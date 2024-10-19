package org.openmrs.module.mycashier.web.controller;

import org.openmrs.module.mycashier.*;

import org.openmrs.module.mycashier.api.*;
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
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ApprovisionnementService approvisionnementService;
	
	//------------------Search client Controller------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/searchClient.form", method = RequestMethod.GET, produces = "application/json")
	public List<ClientResponse> searchClient(@RequestParam("query") String query) {
		System.out.println("Début méthode");
		List<Client> clients = clientService.searchClients(query);
		List<ClientResponse> clientResponses = new ArrayList<>();

		for (Client client : clients) {
			ClientResponse clientResponse = ClientResponse.clientToResponse(client);
			clientResponses.add(clientResponse);
			System.out.println("client Date : " + client.getBirthDate());
			System.out.println("clientResponse Date : " + clientResponse.getBirthDate());
		}

		return clientResponses;
	}
	
	//--------------------RECHERCHE DE DRUGS-------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/searchDrug.form", method = RequestMethod.GET, produces = "application/json")
	public List<DrugResponse> searchDrug(@RequestParam("query") String query) {
		List<DrugResponse> responseList = new ArrayList<DrugResponse>();
		List<MyDrug> drugs = myDrugService.searchDrugs(query);
		
		for (MyDrug drug : drugs) {
			DrugResponse drugResponse = DrugResponse.drugToResponse(drug);
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
			@RequestParam(value = "validate", required = false) Integer validate,
			@RequestParam(value = "query", required = false) String query) throws ParseException {
		System.out.println("Entrée dans le controleur de recherche vente");
		System.out.println("Stratdate :" + startDateStr);
		System.out.println("Enddate :" + endDateStr);
		System.out.println("clientNom :" + clientNom);
		System.out.println("clientPrenom :" + clientPrenom);
		System.out.println("query :" + query);
		System.out.println("validate :" + validate);

		LocalDateTime startDate = LocalDateTime.now().plusMinutes(24);
		LocalDateTime endDate  = LocalDateTime.now().minusHours(72);

       if (validate == null) {
            validate = 0;
       }

		// Vérifier et convertir la date de début
		if (startDateStr != null && !startDateStr.isEmpty()) {
			LocalDate date = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1);
			startDate = date.atStartOfDay();
		}

		// Vérifier et convertir la date de fin
		if (endDateStr != null && !endDateStr.isEmpty()) {
			LocalDate date = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusDays(1);
			endDate = date.atStartOfDay();
		}

		List<VenteDrugResponse> responseList = new ArrayList<>();

		// Rechercher les ventes en fonction des critères donnés
		List<VenteDrug> ventes = venteDrugService.searchVentes( endDate,
				startDate, clientNom, clientPrenom, query, validate);


		System.out.println( " Les ventes :" + ventes);
		System.out.println( " Le nombre d'éléments de ventes :" + ventes.size());
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
		System.out.println( " La longueur de  reponseList :" + responseList.size());

		return responseList;
	}
	
	//---------------------------RECHERCHE PAYMENTS --------------------------------------------------------------------
	
	@ResponseBody
	@RequestMapping(value = "/searchPaymentDrug.form", method = RequestMethod.GET, produces = "application/json")
	public List<PaymentDrugResponse> searchPaymentDrug(
			@RequestParam(value = "startDate", required = false) String startDateStr,
			@RequestParam(value = "endDate", required = false) String endDateStr,
			@RequestParam(value = "clientNom", required = false) String clientNom,
			@RequestParam(value = "clientPrenom", required = false) String clientPrenom,
			@RequestParam(value = "operationType", required = false) String operationType,
			@RequestParam(value = "agentName", required = false) String agentName,
			@RequestParam(value = "assurance", required = false) String assurance) throws ParseException {

		System.out.println("Entrée dans le controleur de recherche des paiements");
		System.out.println("StartDate :" + startDateStr);
		System.out.println("EndDate :" + endDateStr);
		System.out.println("clientNom :" + clientNom);
		System.out.println("clientPrenom :" + clientPrenom);
		System.out.println("operationType :" + operationType);
		System.out.println("agentName :" + agentName);
		System.out.println("assurance :" + assurance);

		LocalDateTime startDate = LocalDateTime.now().minusDays(7); // Période par défaut (dernière semaine)
		LocalDateTime endDate = LocalDateTime.now();

		// Vérifier et convertir la date de début
		if (startDateStr != null && !startDateStr.isEmpty()) {
			LocalDate date = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			startDate = date.atStartOfDay();
		}

		// Vérifier et convertir la date de fin
		if (endDateStr != null && !endDateStr.isEmpty()) {
			LocalDate date = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			endDate = date.atTime(23, 59, 59);
		}

		List<PaymentDrugResponse> responseList = new ArrayList<>();

		// Rechercher les paiements en fonction des critères donnés
		List<Payment> payments = paymentService.searchPayments(
				startDate, endDate, clientNom, clientPrenom, operationType, agentName, assurance);

		System.out.println("Les paiements récupérés : " + payments.size());

		for (Payment payment : payments) {
			try {
				// Convertir Payment en PaymentDrugResponse
				PaymentDrugResponse paymentResponse = PaymentDrugResponse.paymentToPaymentResponse(payment);
				responseList.add(paymentResponse);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors du traitement du paiement : " + payment.getId());
			}
		}

		System.out.println("Liste de réponses des paiements : " + responseList.size());
		System.out.println("Les reponses list  : " + responseList);
		return responseList;
	}
	
	//----------------------RECHERCHE DE MY DRUG EMBALLAGE----------------------------------------------------------------
	
	@ResponseBody
	@RequestMapping(value = "/searchMyDrugEmballage.form", method = RequestMethod.GET, produces = "application/json")
	public List<MyDrugEmballageDTO> searchMyDrugEmballages(

			@RequestParam(value = "medicament", required = false) String medicament,
			@RequestParam(value = "emballage", required = false) String emballage,
			@RequestParam(value = "forme", required = false) String forme,
			@RequestParam(value = "assurance", required = false) String assurance) throws ParseException {

		System.out.println("Entrée dans le controleur de recherche des paiements");
		System.out.println("medicament :" + medicament);
		System.out.println("emballage :" + emballage);
		System.out.println("forme :" + forme);
		System.out.println("assurance :" + assurance);

		List<MyDrugEmballageDTO> responseList = new ArrayList<>();

		// Rechercher les my drug emballages  en fonction des critères donnés
		List<AssuranceMyDrugPrice> assuranceMyDrugPriceList= myDrugService.searchAssuranceMyDrugPrice(medicament,
				emballage, forme, assurance);

		System.out.println("Taille des  éléments récupérés : " + assuranceMyDrugPriceList.size());

		for (AssuranceMyDrugPrice assuranceMyDrugPrice : assuranceMyDrugPriceList) {
			try {
				// Recupérer myDrugEmballage
				MyDrugEmballage myDrugEmballage = assuranceMyDrugPrice.getMyDrugEmballage();
				MyDrugEmballageDTO myDrugEmballageDTO = MyDrugEmballageDTO.convertToDTO(myDrugEmballage)  ;
				myDrugEmballageDTO.setPrixAssurance(assuranceMyDrugPrice.getPrice());

				responseList.add(myDrugEmballageDTO);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors du traitement de la recherche: " );
			}
		}

		System.out.println("Liste de réponses des myDrugEmballageDTO : " + responseList.size());
		System.out.println("Les reponses list  : " + responseList);
		return responseList;
	}
	
	//----------------------RECHERCHE D'APPROVISIONNEMENT----------------------------------------------------------------
	
	@ResponseBody
	@RequestMapping(value = "/searchApprovisionnement.form", method = RequestMethod.GET, produces = "application/json")
	public List<LigneApprovisDTO> searchApprovisionnement(

			@RequestParam(value = "dateDebut", required = false) String dateDebut,
			@RequestParam(value = "dateFin", required = false) String dateFin,
			@RequestParam(value = "medicament", required = false) String medicament,
			@RequestParam(value = "numeroLot", required = false) String numeroLot,
			@RequestParam(value = "entrepotSourceId", required = false) Integer entrepotSourceId,
			@RequestParam(value = "entrepotCibleId", required = false) Integer entrepotCibleId,
			@RequestParam(value = "perimeAvant", required = false) String perimeAvant) throws ParseException {

		System.out.println("Entrée dans le controleur de recherche des approvisionnements");
		System.out.println("medicament :" + medicament);
		System.out.println("dateDebut :" + dateDebut);
		System.out.println("dateFin :" + dateFin);
		System.out.println("numeroLot :" + numeroLot);
		System.out.println("entrepotSourceId :" + entrepotSourceId);
		System.out.println("entrepotCibleId :" + entrepotCibleId);
		System.out.println("perimeAvant :" + perimeAvant);

		List<LigneApprovisDTO> responseList = new ArrayList<>();

		// Rechercher les lignes approvionnements en fonction des critères donnés
		List<LigneApprovis> ligneApprovisList= approvisionnementService.searchLigneApprovis(medicament,
				dateDebut, dateFin, numeroLot, entrepotSourceId, entrepotCibleId, perimeAvant);

		System.out.println("Taille des  éléments récupérés : " + ligneApprovisList.size());

		for (LigneApprovis ligneApprovis : ligneApprovisList) {
			try {
				// Recupérer LigneApprovis
				LigneApprovisDTO ligneApprovisDTO = LigneApprovisDTO.convertToDTO(ligneApprovis)  ;
				ligneApprovisDTO.setDateApprovisionnement(ligneApprovis.getApprovisionnement().getDateTimeApprovisionnement());
				ligneApprovisDTO.setEntrepotSource(ligneApprovis.getApprovisionnement().getEntrepotSource());
				ligneApprovisDTO.setEntrepotCible(ligneApprovis.getApprovisionnement().getEntrepotCible());

				responseList.add(ligneApprovisDTO);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors du traitement de la recherche: " );
			}
		}

		System.out.println("Liste de réponses des ligneApprovisDTO : " + responseList.size());
		System.out.println("Les reponses list  : " + responseList);
		return responseList;
	}
	
	public class StockController {
		
		//----------------------RECHERCHE DE STOCK ENTREPOT ----------------------------------------------------------------
		@ResponseBody
		@RequestMapping(value = "/searchStockEntrepot.form", method = RequestMethod.GET, produces = "application/json")
		public List<MyDrugEmballageDTO> searchStockEntrepot(
				@RequestParam(value = "medicament", required = false) String medicament,
				@RequestParam(value = "numeroLot", required = false) String numeroLot,
				@RequestParam(value = "entrepotId", required = false) Integer entrepotId,
				@RequestParam(value = "assuranceId", required = false) Integer assuranceId,
				@RequestParam(value = "emballageId", required = false) Integer emballageId,
				@RequestParam(value = "perimeAvant", required = false) String perimeAvant,
				@RequestParam(value = "forme", required = false) String forme) throws ParseException {

			System.out.println("Entrée dans le controleur de recherche des Strock entrepot");
			System.out.println("medicament :" + medicament);
			System.out.println("entrepotId :" + entrepotId);
			System.out.println("assuranceId :" + assuranceId);
			System.out.println("numeroLot :" + numeroLot);
			System.out.println("emballageId :" + emballageId);
			System.out.println("perimeAvant :" + perimeAvant);
			System.out.println("forme :" + forme);

			List<MyDrugEmballageDTO> responseList = new ArrayList<>();

			// Rechercher les lignes Stock entrepot en fonction des critères donnés
			List<StockEntrepot> stockEntrepotList = entrepotService.searchStockEntrepot(medicament,
					entrepotId, assuranceId, numeroLot, emballageId, forme, perimeAvant);

			System.out.println("Taille des  éléments récupérés : " + stockEntrepotList.size());

			for (StockEntrepot stockEntrepot : stockEntrepotList) {
				try {
					MyDrugEmballage myDrugEmballage = stockEntrepot.getMyDrugEmballage();
					// Recupérer stockEntrepot
					MyDrugEmballageDTO myDrugEmballageDTO = MyDrugEmballageDTO.convertToDTO(myDrugEmballage);
					myDrugEmballageDTO.setDatePeremption(stockEntrepot.getDatePeremption());
					myDrugEmballageDTO.setNumeroLot(stockEntrepot.getNumeroLot());
					AssuranceMyDrugPrice assuranceMyDrugPrice =
							myDrugService.getAssuranceMyDrugPriceByMyDrugEmballageAndAssuranceId(
									myDrugEmballage, assuranceId);
					myDrugEmballageDTO.setPrixAssurance(assuranceMyDrugPrice.getPrice());

					responseList.add(myDrugEmballageDTO);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Erreur lors du traitement de la recherche: ");
				}
			}

			System.out.println("Liste de réponses des StockEntrepotDTO : " + responseList.size());
			System.out.println("Les reponses list  : " + responseList);
			return responseList;
		}
	}
}
