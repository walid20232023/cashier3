package org.openmrs.module.mycashier.web.controller;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.mycashier.*;
import org.openmrs.module.mycashier.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
	
	@Autowired
	private PaymentService paymentService;
	
	//______________________________AFFICHAGE ACCUEIL PHARMACIE____________________________________________________________________
	@RequestMapping(value = "/accueilPharmacie.form", method = RequestMethod.GET)
	public String accueilPharmacieView(ModelMap model) {
		
		return "module/mycashier/accueilPharmacie"; // Assurez-vous que cette vue existe dans le bon dossier
	}
	
	//______________________________VENTE PHARMACIE____________________________________________________________________
	
	@RequestMapping(value = "/venteProduit.form", method = RequestMethod.GET)
	public String venteProduitForm(@RequestParam(value = "venteDrugId", required = false) Integer venteDrugId, ModelMap model) {

		// Si venteDrugId est présent, l'ajouter au modèle
		if (venteDrugId != null) {
			System.out.println("L'id de la vente cliquée : " + venteDrugId);

			// Récupérer l'objet VenteDrug via l'id
			VenteDrug venteDrug = venteDrugService.getVenteDrugById(venteDrugId);

			// Récupérer le client associé à la vente
			Client client= venteDrug.getClient();
			System.out.println("Client : " + client);

			// Récupérer les IDs des médicaments associés à la vente
			List<LigneVenteDrug> ligneVenteDrugList = venteDrugService.getAllLigneVenteDrug(venteDrug);
			//System.out.println("Les drugIds : " + myDrugEmballageIds);
			List<MyDrugEmballageDTO> drugEmballageDTOList = new ArrayList<>();
			for (LigneVenteDrug ligneVenteDrug  : ligneVenteDrugList)  {
				Integer myDrugEmballageId = ligneVenteDrug.getMyDrugEmballage().getId();
				MyDrugEmballage myDrugEmballage = myDrugService.getMyDrugEmballageById(myDrugEmballageId);   ;
				MyDrugEmballageDTO myDrugEmballageDTO = MyDrugEmballageDTO.convertToDTO(myDrugEmballage);
				Integer stockVente = entrepotService.getStockEntrepotByDrugEmballageAndEntrepot(EntrepotConstants.VENTE_ID, myDrugEmballageId, ligneVenteDrug.getId().getNumeroLot())
						.getQuantiteStock();

				Integer stockMagasin = entrepotService.getStockEntrepotByDrugEmballageAndEntrepot(EntrepotConstants.MAGASIN_ID, myDrugEmballageId, ligneVenteDrug.getId().getNumeroLot())
						.getQuantiteStock();
				myDrugEmballageDTO.setStockVente(stockVente);
				myDrugEmballageDTO.setStockMagasin(stockMagasin);
				myDrugEmballageDTO.setQuantiteVendue(ligneVenteDrug.getQuantity());
				drugEmballageDTOList.add( myDrugEmballageDTO) ;
			}
			model.addAttribute("drugs", drugEmballageDTOList)  ;


			// Récupérer l'assurance utilisée (si applicable)
			String assurance = venteDrug.getAssurance();
			System.out.println("Assurance : " + assurance);

			// Ajouter toutes les informations au modèle pour la vue
			model.addAttribute("venteDrugId", venteDrugId);
			model.addAttribute("client", ClientResponse.clientToResponse(client));
			model.addAttribute("assurance", assurance);
		} else {
			// Si venteDrugId est null, ne rien initialiser ou ajouter d'autres valeurs par défaut si nécessaire
			System.out.println("Pas de venteDrugId fourni.");
		}

		return "module/mycashier/venteProduit"; // Assurez-vous que cette vue existe dans le bon dossier
	}
	
	//-----------------CREATION CLIENT--------------------------------------------------------------------------------
	
	@RequestMapping(value = "/client.form", method = RequestMethod.GET)
	public String showCientForm(@RequestParam(value = "id", required = false) Integer clientId, Model model) {
		Client client = clientId != null ? clientService.getClientById(clientId) : new Client();
		ClientResponse clientResponse = ClientResponse.clientToResponse(client);
		model.addAttribute("client", clientResponse);
		
		return "module/mycashier/clientForm";
	}
	
	//--------------------SAUVEGARDE CLIENT-----------------------------------------
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
	
	//-------------------------LISTE CLIENTS--------------------------------------------------
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
	        @RequestParam(value = "venteDrugId", required = false) Integer venteDrugId,
	        @RequestParam(value = "assuranceSelectionneeValue", required = false) String assurance,
	        @RequestParam("partAssurance") Float partAssurance,
	        @RequestParam("total") String[] totalArray,
	        @RequestParam("numeroLots") String[] numeroLots,
	        @RequestParam("medicamentIds") String[] medicamentIdsArray, // Change here to String[]
	        @RequestParam("quantity") String[] quantites, @RequestParam("pu") String[] prices,
	        @RequestParam("validate") Integer validate, Model model) {

		VenteDrug venteDrug;
		
		// Si venteDrugId est présent, récupérer la vente existante
		if (venteDrugId != null) {
			System.out.println("L'id de la vente cliquée : " + venteDrugId);
			venteDrug = venteDrugService.getVenteDrugById(venteDrugId);
			System.out.println("Vente récupérée : " + venteDrug);
		} else {
			// Sinon, créer une nouvelle instance de VenteDrug
			venteDrug = new VenteDrug();
			
			System.out.println("Nouvelle instance de vente créée.");
		}
		
		try {
			// Vérifier que les listes ont la même taille
			
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
					// Gérer l'erreur iciwaliy2023
					
					System.out.println("Erreur de conversion de la valeur total en float: " + e.getMessage());
				}
				
			}
			//  Attribution du total
			venteDrug.setTotal(total);
			
			//Initialisation du reste
			venteDrug.setReste(total - partAssurance);
			
			// Supprimer les lignes de vente existantes
			
			System.out.println("avant existing");
			
			venteDrugService.deleteAllLigneVente(venteDrugId);
			
			System.out.println("après delet  existing");

			// Traiter les médicaments sélectionnés
			for (int i = 0; i < medicamentIdsArray.length; i++) {
				// Récupérer l'ID du médicament en utilisant l'index i
				String medicamentIdStr = medicamentIdsArray[i];
				
				// Convertir l'ID en entier
				Integer myDrugEmballageId = Integer.parseInt(medicamentIdStr.trim());
				
				// Recupérer quantité
				String quantityStr = quantites[i];
				Integer quantity = Integer.parseInt(quantityStr.trim());
				
				// Recupérer prix
				String priceStr = prices[i];
				Integer price = Integer.parseInt(priceStr.trim());
				
				MyDrugEmballage myDrugEmballage = myDrugService.getMyDrugEmballageById(myDrugEmballageId);
				
				LigneVenteDrug ligneVenteDrug = new LigneVenteDrug();
				
				LigneVenteDrug.LigneVenteDrugId ligneVenteDrugId = new LigneVenteDrug.LigneVenteDrugId(venteDrug.getId(),
						myDrugEmballage.getId(), numeroLots[i]);
				ligneVenteDrug.setId(ligneVenteDrugId);
				
				System.out.println("Contenu ligneVENTE :" + ligneVenteDrug);
				
				System.out.println("DRug ID :" + myDrugEmballageId);
				
				//Modification de ligneService
				
				System.out.println("MyDRug :" + myDrugEmballage);
				//ligneVenteDrug.setMyDrug(myDrug);
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
			
			if (Objects.equals(validate, Integer.valueOf(1))) {
				venteDrug.setValidate(DateFormater.VALIDATE_TRUE);
				venteDrugService.saveVenteDrug(venteDrug);
				return "module/mycashier/validatedDrugsList";
			} else {
				// Rediriger vers le formulaire avec l'ID de la vente ajoutée dans l'URL
				venteDrug.setValidate(DateFormater.VALIDATE_FALSE);
				venteDrugService.saveVenteDrug(venteDrug);
				return "redirect:/module/mycashier/venteProduit.form?venteDrugId=" + venteDrug.getId();
				
			}
			
		}
		catch (Exception e) {
			model.addAttribute("error", "Problème lors de l'enregistrement: " + e.getMessage());
			
			System.out.println("Je suis dans le catch");
			return "module/mycashier/venteProduit";
			
		}
		
	}
	
	//----------------------------VALIDATE VENTE ------------------------------------------------------------------------------
	
	// --------------------Méthode pour afficher la vue de la liste des ventes de médicaments----------------------------------
	@RequestMapping(value = "/venteProduitList.form", method = RequestMethod.GET)
	public String venteDrugListForm(ModelMap model) {
		// Vous pouvez ajouter des attributs au modèle ici si nécessaire
		// Par exemple, model.addAttribute("someAttribute", someValue);
		
		return "module/mycashier/venteProduitList"; // Assurez-vous que cette vue existe dans le bon dossier
	}
	
	// --------------------Méthode pour afficher la liste des médicaments validés----------------------------------
	@RequestMapping(value = "/validatedDrugsList.form", method = RequestMethod.GET)
	public String validatedDrugsListForm(ModelMap model) {
		// Vous pouvez ajouter des attributs au modèle ici si nécessaire
		// Par exemple, model.addAttribute("someAttribute", someValue);
		
		return "module/mycashier/validatedDrugsList"; // Assurez-vous que cette vue existe dans le bon dossier
	}
	
	//----------------------------DRUG PAYMENT AFFICHAGE---------------------------------------------
	
	@RequestMapping(value = "/paymentDrug.form", method = RequestMethod.GET)
	public String showPaymentForm(@RequestParam("venteDrugId") Integer venteDrugId, Model model) {
		// Récupérer l'objet Vente via l'ID
		VenteDrug venteDrug = venteDrugService.getVenteDrugById(venteDrugId);
		
		// Ajouter l'objet Vente au modèle pour être utilisé dans la vue
		model.addAttribute("vente", venteDrug);
		
		//Les autres parmètres , pêle mêle
		Client client = venteDrug.getClient();
		String nomClient = client.getName();
		String prenomClient = client.getFirstnames();
		String sexeClient = client.getSex();
		Date birthDate = client.getBirthDate();
		
		//Ajouts des attributes
		model.addAttribute("clientNom", nomClient);
		model.addAttribute("clientPrenom", prenomClient);
		model.addAttribute("clientSexe", sexeClient);
		model.addAttribute("clientDob", birthDate);
		
		// Renvoyer la vue correspondant au formulaire de paiement
		return "module/mycashier/paymentDrug";
	}
	
	//----------------SAUVEGARDE PAIEMENT----------------------------------------------------------------------------------
	
	@RequestMapping(value = "/processPayment", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public String processPayment(@RequestParam("venteDrugId") Integer venteDrugId,
	        @RequestParam("modePaiement") String modePaiement, @RequestParam("sommePayee") Float sommePayee,
	        @RequestParam("resteAPayer") Float resteAPayer, Model model) {
		
		try {
			Payment payment = new Payment();
			
			// Récupération de l'utilisateur authentifié
			User user = Context.getAuthenticatedUser();
			Agent agent = agentService.getAgentByUserId(user);
			VenteDrug venteDrug = venteDrugService.getVenteDrugById(venteDrugId);
			
			// Affectation des attributs du paiement
			payment.setAgent(agent);
			payment.setVenteDrug(venteDrug);
			payment.setDatePayment(LocalDateTime.now());
			payment.setMontant(sommePayee);
			payment.setModePayment(modePaiement);
			
			// Affectation du reste à payer
			venteDrug.setReste(resteAPayer);
			
			//Reaffectation de la date de vente
			venteDrug.setDateVente(LocalDateTime.now());
			
			// Mise à jour de la caisse de l'agent
			//Float caisse = agent.getCaisse();
			Float caisse = Float.valueOf(0);
			agent.setCaisse(caisse + sommePayee);
			
			// Sauvegarde du paiement
			paymentService.savePayment(payment);
			
			// Si le reste à payer est inférieur ou égal à 0, retour à la liste des ventes
			if (resteAPayer <= 0) {
				return "module/mycashier/searchPayments";
			} else {
				return "redirect:/module/mycashier/validatedDrugsList.form";
			}
		}
		catch (Exception e) {
			// En cas d'erreur, une exception sera levée et la transaction sera annulée
			throw new RuntimeException("Erreur lors du traitement du paiement : " + e.getMessage(), e);
		}
	}
	
	//-------------------AFFICHAGE RECHERCHE PAIEMENTS-------------------------------------------------------------
	
	@RequestMapping(value = "/searchPaymentDrugForm.form", method = RequestMethod.GET)
	public String searchPaymentDrugForm(ModelMap model) {
		// Vous pouvez ajouter des attributs au modèle ici si nécessaire pour la vue
		
		return "module/mycashier/searchPayments"; // Assurez-vous que ce fichier JSP existe à cet emplacement
	}
	
}
