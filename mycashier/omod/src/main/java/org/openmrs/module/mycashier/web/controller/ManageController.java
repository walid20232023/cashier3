package org.openmrs.module.mycashier.web.controller;

import org.openmrs.Drug;
import org.openmrs.api.ConceptService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.mycashier.*;
import org.openmrs.module.mycashier.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/module/mycashier")
public class ManageController {
	
	@Autowired
	private AssuranceService assuranceService;
	
	//______________________________AFFICHAGE ASSURANCES____________________________________________________________________
	@RequestMapping(value = "/assuranceList.form", method = RequestMethod.GET)
	public String assuranceListView(ModelMap model) {
		List<Assurance> assuranceList = null;
		try {
			assuranceList = assuranceService.getAllAssurances();
		}
		catch (Exception exception) {}
		// Ajouter la liste au modèle
		model.addAttribute("assuranceList", assuranceList);
		return "module/mycashier/assuranceList"; // Assurez-vous que cette vue existe dans le bon dossier
	}
	
	//______________________AJOUT ASSURANCE----------------------------------------------------------------------
	
	@RequestMapping(value = "/assuranceForm.form", method = RequestMethod.GET)
	public String showAddOrUpdateAssuranceForm(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
		if (id != null) {
			Assurance assurance = assuranceService.getAssuranceById(id);
			if (assurance != null) {
				model.addAttribute("assurance", assurance);
			} else {
				model.addAttribute("errorMessage", "Assurance not found with ID: " + id);
			}
		} else {
			
		}
		return "module/mycashier/assuranceForm";
	}
	
	@RequestMapping(value = "/assuranceForm.form", method = RequestMethod.POST)
	public String handleAddOrUpdateAssuranceForm(@RequestParam(value = "id", required = false) Integer id,
	        @RequestParam("name") String name, @RequestParam("address") String address,
	        @RequestParam("telephone") String telephone, ModelMap model) {
		
		try {
			Assurance assurance;
			if (id != null) {
				// Retrieve the existing Assurance
				assurance = assuranceService.getAssuranceById(id);
				if (assurance == null) {
					// If the assurance with the provided ID does not exist, add an error message
					model.addAttribute("errorMessage", "Assurance not found with ID: " + id);
					return "module/mycashier/assuranceForm";
				}
			} else {
				// Create a new Assurance
				assurance = new Assurance();
				assurance.setUserId(Context.getAuthenticatedUser().getUserId());
				
			}
			
			// Set the fields from the form
			assurance.setName(name);
			assurance.setAddress(address);
			assurance.setTelephone(telephone);
			
			// Save the Assurance using the service
			assuranceService.saveAssurance(assurance);
			
			// Redirect to the list of assurances after successful addition/update
			return "redirect:/module/mycashier/assuranceList.form";
		}
		catch (Exception e) {
			// If there is an error, add an error message to the model
			model.addAttribute("errorMessage", "Error saving assurance: " + e.getMessage());
			return "module/mycashier/assuranceForm";
		}
	}
}
