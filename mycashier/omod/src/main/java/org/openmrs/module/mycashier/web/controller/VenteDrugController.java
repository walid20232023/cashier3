package org.openmrs.module.mycashier.web.controller;

import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.Client;
import org.openmrs.module.mycashier.ClientAssurance;

import org.openmrs.module.mycashier.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/module/mycashier")
public class VenteDrugController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AssuranceService assuranceService;
	
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
	public String saveClient(@ModelAttribute("client") Client client,
	        @RequestParam(value = "assuranceList", required = false) List<Integer> assuranceList, HttpServletRequest request) {
		client.setUserId(Context.getAuthenticatedUser().getUserId());
		
		// Calculate age from birth date
		if (client.getBirthDate() != null) {
			client.setAge(calculateAge(client.getBirthDate()));
		} else if (client.getAge() != null) {
			client.setBirthDate(calculateBirthDate(client.getAge()));
		}
		
		// Clear existing assurances
		List<Assurance> existingAssurances = clientService.getAssurancesByClient(client);
		for (Assurance assurance : existingAssurances) {
			clientService.deleteAssuranceFromClient(assurance, client);
		}
		
		// Add new assurances
		if (assuranceList != null) {
			for (Integer assuranceId : assuranceList) {
				Assurance assurance = assuranceService.getAssuranceById(assuranceId);
				if (assurance != null) {
					clientService.addAssuranceToClient(assurance, client);
				}
			}
		}
		
		// Save client
		clientService.saveClient(client);
		
		return "redirect:/module/mycashier/client/list";
	}
	
	@RequestMapping(value = "/clientList.form", method = RequestMethod.GET)
	public String listClients(Model model) {
		List<Client> clients = clientService.getAllClients();
		model.addAttribute("clients", clients);
		return "module/mycashier/clientList";
	}
	
	private int calculateAge(Date birthDate) {
		// Calculer l'âge à partir de la date de naissance
		return new Date().getYear() - birthDate.getYear();
	}
	
	private Date calculateBirthDate(int age) {
		// Calculer la date de naissance à partir de l'âge
		Date birthDate = new Date();
		birthDate.setYear(new Date().getYear() - age);
		return birthDate;
	}
	
}
