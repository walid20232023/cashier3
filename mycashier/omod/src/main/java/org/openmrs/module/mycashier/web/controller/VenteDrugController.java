package org.openmrs.module.mycashier.web.controller;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.mycashier.*;
import org.openmrs.module.mycashier.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/module/mycashier")
public class VenteDrugController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AssuranceService assuranceService;
	
	@Autowired
	private VenteDrugService venteDrugService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private EntrepotService entrepotService;
	
	@Autowired
	private MyDrugService myDrugService;
	
	//______________________________AFFICHAGE ACCUEIL PHARMACIE____________________________________________________________________
	@RequestMapping(value = "/accueilPharmacie.form", method = RequestMethod.GET)
	public String accueilPharmacieView(ModelMap model) {
		
		return "module/mycashier/accueilPharmacie"; // Assurez-vous que cette vue existe dans le bon dossier
	}
	
	//______________________________VENTE PHARMACIE____________________________________________________________________
	
	@RequestMapping(value = "/venteProduit.form", method = RequestMethod.GET)
	public String venteProduitForm(ModelMap model) {
		
		return "module/mycashier/venteProduit"; // Assurez-vous que cette vue existe dans le bon dossier
	}
	
	//-----------------CREATION CLIENT--------------------------------------------------------------------------------
	
	@RequestMapping(value = "/client.form", method = RequestMethod.GET)
	public String showCientForm(@RequestParam(value = "id", required = false) Integer clientId, Model model) {
		Client client = clientId != null ? clientService.getClientById(clientId) : new Client();
		model.addAttribute("client", client);
		model.addAttribute("assurances", assuranceService.getAllAssurances());
		return "module/mycashier/clientForm";
	}
	
	@RequestMapping(value = "/client.form", method = RequestMethod.POST)
	public String saveClient(@RequestParam(value = "id", required = false) Integer clientId,
	        @RequestParam("name") String name, @RequestParam("firstnames") String firstnames,
	        @RequestParam("birthDate") String birthDateString, @RequestParam("age") Integer age,
	        @RequestParam("sex") String sex, @RequestParam("address") String address,
	        @RequestParam("telephone") String telephone,
	        @RequestParam(value = "assuranceList", required = false) List<Integer> assuranceList, HttpServletRequest request)
	        throws ParseException {
		
		System.out.println("La birthDate en string :" + birthDateString);
		// Créez un objet Client et définissez ses propriétés
		Client client = clientId != null ? clientService.getClientById(clientId) : new Client();
		client.setName(name);
		client.setFirstnames(firstnames);
		client.setSex(sex);
		
		if (!birthDateString.isEmpty()) {
			client.setBirthDate(ClientResponse.convertStringToDate(birthDateString));
		} else {
			client.setBirthDate(null);
		}
		
		client.setAddress(address);
		client.setTelephone(telephone);
		client.setUserId(Context.getAuthenticatedUser().getUserId());
		
		try {
			// Enregistrez le client
			clientService.saveClient(client);
		}
		catch (Exception e) {
			// Gérer l'exception
			e.printStackTrace(); // Ou loggez l'exception avec un logger approprié
			// Vous pouvez également ajouter du code pour notifier l'utilisateur ou pour des actions de récupération
			System.out.println("Erreur lors de l'enregistrement du client : " + e.getMessage());
		}
		
		// Gérer les assurances
		List<Assurance> existingAssurances = clientService.getAssurancesByClient(client);
		for (Assurance assurance : existingAssurances) {
			clientService.deleteAssuranceFromClient(assurance, client);
		}
		
		if (assuranceList != null) {
			for (Integer assuranceId : assuranceList) {
				clientService.addAssuranceToClient(assuranceService.getAssuranceById(assuranceId), client);
			}
		}
		
		return "redirect:/module/mycashier/venteProduit.form";
	}
	
	@RequestMapping(value = "/clientList.form", method = RequestMethod.GET)
	public String listClients(Model model) {
		
		System.out.println("Dans le controlleur liste client");
		List<Client> clients = clientService.getAllClients();
		model.addAttribute("clients", clients);
		return "module/mycashier/clientList";
	}
	
	//-----------------------SAVE DRUG--------------------------------------------------------------
	
	@RequestMapping(value = "/saveVenteDrug", method = RequestMethod.POST)
	public String saveVenteDrug(@RequestParam("client") Integer clientId,
	        @RequestParam(value = "assuranceSelectionneeValue", required = false) String assurance,
	        @RequestParam("partAssurance") Float partAssurance, @RequestParam("total") String[] totalArray,
	        @RequestParam("medicamentIds") String[] medicamentIdsArray, // Change here to String[]
	        @RequestParam("quantity") String[] quantites, @RequestParam("pu") String[] prices, Model model) {
		
		VenteDrug venteDrug = new VenteDrug();
		
		List<LigneVenteDrug> ligneVenteDrugs = new ArrayList<LigneVenteDrug>();
		
		try {
			// Vérifier que les listes ont la même taille
			/**
			 * if (medicamentIds.size() != quantites.size() || medicamentIds.size() !=
			 * prices.size()) { model.addAttribute("error",
			 * "Mismatch between the number of medications, quantities, and prices"); return
			 * "module/mycashier/venteProduit"; }
			 **/
			if (medicamentIdsArray == null || medicamentIdsArray.length == 0) {
				model.addAttribute("error", "Vous devez ajouter au moins un médicament avant d'enregistrer.");
				return "module/mycashier/venteProduit"; // Redirects to the sales page
			}
			
			// Ajout de clientID
			if (clientId == null) {
				model.addAttribute("error", "Veuillez sélectionner un client");
				//	return "module/mycashier/venteProduit";
				System.out.println("(Client Id :" + clientId);
			} else {
				System.out.println("(Client Id :" + clientId);
				venteDrug.setClient(clientService.getClientById(clientId));
			}
			
			System.out.println("avant user");
			User user = Context.getAuthenticatedUser();
			
			Agent agent = agentService.getAgentByUserId(user);
			
			//Ajout de l'agent
			venteDrug.setAgent(agent);
			
			//Ajout entrepot
			venteDrug.setEntrepot(entrepotService.getEntrepotById(EntrepotConstants.VENTE_ID));
			
			venteDrug.setDateVente(LocalDateTime.now());
			
			// Ajout de assurance
			
			venteDrug.setAssurance(assurance);
			
			//Ajouts part assurance et total
			venteDrug.setPartAssurance(partAssurance);
			
			//Première sauvegarde
			venteDrugService.saveVenteDrug(venteDrug);
			
			// Vérifier si le tableau n'est pas vide et extraire le dernier élément
			float total = 0.0f;
			if (totalArray != null && totalArray.length > 0) {
				// Obtenir le dernier élément du tableau
				String lastTotalStr = totalArray[totalArray.length - 1];
				
				// Remplacer les virgules par des points pour les nombres à virgule flottante
				try {
					total = Float.parseFloat(lastTotalStr.replace(',', '.'));
				}
				catch (NumberFormatException e) {
					// Gérer l'erreur ici
					System.out.println("Erreur de conversion de la valeur total en float: " + e.getMessage());
				}
				
			}
			
			venteDrug.setTotal(total);
			// Supprimer les lignes de vente existantes
			
			System.out.println("avant existing");
			List<LigneVenteDrug> existingLignes = venteDrugService.getAllLignesByVenteDrug(venteDrug);
			System.out.println("après existing");
			
			System.out.println("existainLines :" + existingLignes);
			
			if (existingLignes != null && !existingLignes.isEmpty()) {
				for (LigneVenteDrug ligne : existingLignes) {
					venteDrugService.deleteLigneFromVenteDrug(ligne.getMyDrug(), venteDrug);
				}
			}
			
			System.out.println("après delet  existing");
			
			System.out.println("existainLines :" + existingLignes);
			
			// Traiter les médicaments sélectionnés
			for (int i = 0; i < medicamentIdsArray.length; i++) {
				// Récupérer l'ID du médicament en utilisant l'index i
				String medicamentIdStr = medicamentIdsArray[i];
				
				// Convertir l'ID en entier
				Integer drugId = Integer.parseInt(medicamentIdStr.trim());
				
				// Recupérer quantité
				String quantityStr = quantites[i];
				Integer quantity = Integer.parseInt(quantityStr.trim());
				
				// Recupérer prix
				String priceStr = prices[i];
				Integer price = Integer.parseInt(priceStr.trim());
				
				MyDrug myDrug = myDrugService.getMyDrugById(drugId);
				
				LigneVenteDrug ligneVenteDrug = new LigneVenteDrug();
				
				LigneVenteDrug.LigneVenteDrugId ligneVenteDrugId = new LigneVenteDrug.LigneVenteDrugId(venteDrug.getId(),
				        myDrug.getId());
				ligneVenteDrug.setId(ligneVenteDrugId);
				
				System.out.println("Contenu ligneVENTE :" + ligneVenteDrug);
				
				System.out.println("DRug ID :" + drugId);
				
				//Modification de ligneService
				
				System.out.println("MyDRug :" + myDrug);
				ligneVenteDrug.setMyDrug(myDrug);
				ligneVenteDrug.setVenteDrug(venteDrug);
				
				System.out.println(" Après modif Contenu ligneVENTE :" + ligneVenteDrug);
				ligneVenteDrug.setQuantity(quantity);
				ligneVenteDrug.setPrice(price);
				
				try {
					// Appel à la méthode pour sauvegarder l'objet LigneVenteDrug
					venteDrugService.saveLigneVenteDrug(ligneVenteDrug);
					System.out.println("LigneVenteDrug sauvegardée avec succès.");
				}
				catch (Exception e) {
					// Capture et affichage de l'erreur
					System.err.println("Erreur lors de la sauvegarde de LigneVenteDrug : " + e.getMessage());
					e.printStackTrace(); // Affiche le détail complet de l'exception
				}
				
				System.out.println(" Après enregistrement :" + ligneVenteDrug);
				
				//Ajouter la ligne
				venteDrugService.addLigneToVenteDrug(venteDrug, ligneVenteDrug);
				
			}
			
			model.addAttribute("success", "Vente enregistrée avec succès");
			return "module/mycashier/venteProduit";
			
		}
		catch (Exception e) {
			model.addAttribute("error", "Problème lors de l'enregistrement: " + e.getMessage());
			
			System.out.println("Je suis dans le catch");
			return "module/mycashier/venteProduit";
			
		}
	}
	
}
