package org.openmrs.module.mycashier.web.controller;

import org.openmrs.module.mycashier.*;

import org.openmrs.module.mycashier.api.ClientService;
import org.openmrs.module.mycashier.api.EntrepotService;
import org.openmrs.module.mycashier.api.MyDrugService;
import org.openmrs.module.mycashier.api.VenteDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/module/mycashier")
public class SearchController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MyDrugService myDrugService;
	
	@Autowired
	private EntrepotService entrepotService;
	
	@Autowired
	private VenteDrugService venteDrugService;
	
	/**
	 * //------------------Search client Controller------------------------------------------------
	 * 
	 * @ResponseBody
	 * @RequestMapping(value = "/searchClient", method = RequestMethod.GET) public List<Client>
	 *                       searchClient(@RequestParam("query") String query) {
	 *                       System.out.println("Méthode client appellée"); return
	 *                       clientService.searchClients(query); }
	 **/
	
	//------------------Search drug Controller------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/searchClient.form", method = RequestMethod.GET, produces = "application/json")
	public List<ClientResponse> searchClient(@RequestParam("query") String query) {
		
		System.out.println("Debut méthode");
		List<Client> clients = clientService.searchClients(query);
		List<ClientResponse> clientResponses = new ArrayList<ClientResponse>();
		for (Client client : clients) {
			ClientResponse clientResponse = ClientResponse.clientToResponse(client);
			clientResponses.add(clientResponse);
			
			System.out.println("client Date : " + client.getBirthDate());
			System.out.println("clientResponse Date : " + clientResponse.getBirthDate());
			
		}
		
		return clientResponses;
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchDrug.form", method = RequestMethod.GET, produces = "application/json")
	public List<DrugResponse> searchDrug(@RequestParam("query") String query) {
		List<DrugResponse> responseList = new ArrayList<DrugResponse>();
		List<MyDrug> drugs = myDrugService.searchDrugs(query);
		
		for (MyDrug drug : drugs) {
			DrugResponse drugResponse = new DrugResponse().drugToResponse(drug);
			System.out.println("Nom drugResponse :" + drugResponse.getName());
			
			System.out.println("Avant appel stockVente");
			System.out.println("EntrepoId :" + EntrepotConstants.VENTE_ID);
			System.out.println("DrugId " + drug.getId());
			
			try {
				StockEntrepot stockEntrepot = entrepotService.getStockByEntrepotAndDrug(EntrepotConstants.VENTE_ID,
				    drug.getId());
				System.out.println("Le stockEntrepot :" + stockEntrepot.toString());
				System.out.println("La quantité du stock " + stockEntrepot.getQuantiteStock());
			}
			catch (Exception e) {
				// Gérer l'exception de manière appropriée
				e.printStackTrace(); // Affiche la pile d'erreurs pour le débogage
				System.out.println("Erreur lors de la récupération du stock d'entrepôt pour le médicament ID: "
				        + drug.getId());
			}
			
			//System.out.println("StockVente :" + stockVente);
			Integer stockVente = entrepotService.getStockByEntrepotAndDrug(EntrepotConstants.VENTE_ID, drug.getId())
			        .getQuantiteStock();
			Integer stockMagasin = entrepotService.getStockByEntrepotAndDrug(EntrepotConstants.MAGASIN_ID, drug.getId())
			        .getQuantiteStock();
			//System.out.println("StockMag :" + stockMagasin);
			
			drugResponse.setStockLocal(stockVente);
			drugResponse.setStockMag(stockMagasin);
			
			responseList.add(drugResponse);
		}
		
		System.out.println("La response List :" + responseList);
		return responseList;
	}
	
	// Controlleur de recherche des ventes
	@ResponseBody
	@RequestMapping(value = "/searchVenteDrug.form", method = RequestMethod.GET, produces = "application/json")
	public List<VenteDrugResponse> searchVenteDrug(
			@RequestParam(value = "startDate", required = false) String startDateStr,
			@RequestParam(value = "endDate", required = false) String endDateStr,
			@RequestParam(value = "clientNom", required = false) String clientNom,
			@RequestParam(value = "clientPrenom", required = false) String clientPrenom,
			@RequestParam(value = "query", required = false) String query) throws ParseException {
		System.out.println("Entrée dans le controleur de recherche vente");
		System.out.println("Stratdate :" + startDateStr);
		System.out.println("Enddate :" + endDateStr);
		System.out.println("clientNom :" + clientNom);
		System.out.println("clientPrenom :" + clientPrenom);
		System.out.println("query :" + query);

		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate  = LocalDateTime.now().minusHours(72);



		// Vérifier et convertir la date de début
		if (startDateStr != null && !startDateStr.isEmpty()) {
			LocalDate date = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			startDate = date.atStartOfDay();
		}

		// Vérifier et convertir la date de fin
		if (endDateStr != null && !endDateStr.isEmpty()) {
			LocalDate date = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			endDate = date.atStartOfDay();
		}

		List<VenteDrugResponse> responseList = new ArrayList<>();

		// Rechercher les ventes en fonction des critères donnés
		List<VenteDrug> ventes = venteDrugService.searchVentes( endDate,
				startDate, clientNom, clientPrenom, query);
		System.out.println( " Les ventes :" + ventes);

		for (VenteDrug vente : ventes) {
			try {
				Integer venteId = vente.getId();
				System.out.println("L'Id de la vente :" + venteId);
				List<Integer> drugIds = venteDrugService.getAllLignesByVenteDrug(vente) ;
				List <MyDrug> drugs  = new ArrayList<>();
				for ( Integer drugId : drugIds  ) {
					MyDrug myDrug = myDrugService.getMyDrugById(drugId);
					drugs.add(myDrug);

				}// Supposons que cette méthode existe
				System.out.println("Les drugs récupérée: " + drugIds);

				VenteDrugResponse venteDrugResponse = new VenteDrugResponse().venteToResponse(vente, drugs);
				System.out.println("VenteDrugResponse créé: " + venteDrugResponse);
				responseList.add(venteDrugResponse ) ;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors du traitement de la vente: " + vente);
			}

		}
		System.out.println( " Les reponseList :" + responseList);

		return responseList;
	}
}
