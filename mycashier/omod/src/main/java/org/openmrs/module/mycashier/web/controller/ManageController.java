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

	@Autowired
	private ServService servService;

	@Autowired
	private EmballageService emballageService;
	
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

	//______________________________AFFICHAGE TYPE SERVICE____________________________________________________________________
	@RequestMapping(value = "/typeService.form", method = RequestMethod.GET)
	public String typeServiceListView(ModelMap model) {
		List<TypeService> typeServices = null;
		try {
			typeServices = servService.getAllTypeServices();
		}
		catch (Exception exception) {}
		// Ajouter la liste au modèle
		model.addAttribute("typeServiceList", typeServices);
		return "module/mycashier/typeService"; // Assurez-vous que cette vue existe dans le bon dossier
	}

	//______________________AJOUT TYPE SERVICE----------------------------------------------------------------------

	@RequestMapping(value = "/typeServiceForm.form", method = RequestMethod.GET)
	public String showAddOrUpdateTypeServiceForm(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
		if (id != null) {
			TypeService typeService = servService.getTypeServiceById(id);
			if (typeService!= null) {
				model.addAttribute("typeService",typeService);
			} else {
				model.addAttribute("errorMessage", "TypeService not found with ID: " + id);
			}
		} else {

		}
		return "module/mycashier/typeServiceForm";
	}

	@RequestMapping(value = "/typeService.form", method = RequestMethod.POST)
	public String handleAddOrUpdateTypeServiceForm(@RequestParam(value = "id", required = false) Integer id,
												 @RequestParam("name") String name,  ModelMap model) {

		try {
			TypeService typeService;
			if (id != null) {
				// Retrieve the existing Assurance
				typeService= servService.getTypeServiceById(id);
				if (typeService == null) {
					// If the assurance with the provided ID does not exist, add an error message
					model.addAttribute("errorMessage", "TypeAssurance not found with ID: " + id);
					return "module/mycashier/typeServiceForm";
				}
			} else {
				// Create a new Assurance
				typeService = new TypeService();
				typeService.setUserId(Context.getAuthenticatedUser().getUserId());
			}
			// Set the fields from the form
			typeService.setName(name);
			// Save the Assurance using the service
			servService.saveTypeService(typeService);

			// Redirect to the list of assurances after successful addition/update
			return "redirect:/module/mycashier/typeServiceList.form";
		}
		catch (Exception e) {
			// If there is an error, add an error message to the model
			model.addAttribute("errorMessage", "Error saving TypeAssurance: " + e.getMessage());
			return "module/mycashier/typeServiceForm";
		}
	}



	//______________________________AFFICHAGE SERVICE____________________________________________________________________
	@RequestMapping(value = "/service.form", method = RequestMethod.GET)
	public String serviceListView(ModelMap model) {
		List<MyService> myServices = null;
		try {
			myServices = servService.getAllServices();
		}
		catch (Exception exception) {}
		// Ajouter la liste au modèle
		model.addAttribute("serviceList", myServices);
		return "module/mycashier/service"; // Assurez-vous que cette vue existe dans le bon dossier
	}

	//______________________AJOUT SERVICE----------------------------------------------------------------------

	@RequestMapping(value = "/typeServiceForm.form", method = RequestMethod.GET)
	public String showAddOrUpdateServiceForm(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
		if (id != null) {
			TypeService typeService = servService.getTypeServiceById(id);
			if (typeService!= null) {
				model.addAttribute("typeService",typeService);
			} else {
				model.addAttribute("errorMessage", "TypeService not found with ID: " + id);
			}
		} else {

		}
		return "module/mycashier/typeServiceForm";
	}

	@RequestMapping(value = "/service.form", method = RequestMethod.POST)
	public String handleAddOrUpdateServiceForm(@RequestParam(value = "id", required = false) Integer id,
											   @RequestParam("name") String name,
											   @RequestParam("type_service_id") Integer typeServiceId,
											   @RequestParam("price") Integer price,
											   ModelMap model) {

		try {
			MyService myService;
			if (id != null) {
				// Retrieve the existing Assurance
				myService= servService.getServiceById(id);
				if (myService == null) {
					// If the assurance with the provided ID does not exist, add an error message
					model.addAttribute("errorMessage", "Service not found with ID: " + id);
					return "module/mycashier/serviceForm";
				}
			} else {
				// Create a new Assurance
				myService = new MyService();
				myService.setUserId(Context.getAuthenticatedUser().getUserId());
			}
			// Set the fields from the form
			myService.setName(name);
			myService.setTypeService(servService.getTypeServiceById(typeServiceId));
			myService.setPrice(Long.valueOf(price));

			// Save the Assurance using the service
			servService.saveService(myService);

			// Redirect to the list of assurances after successful addition/update
			return "redirect:/module/mycashier/serviceList.form";
		}
		catch (Exception e) {
			// If there is an error, add an error message to the model
			model.addAttribute("errorMessage", "Error saving Service: " + e.getMessage());
			return "module/mycashier/serviceForm";
		}
	}





	//______________________________AFFICHAGE EMBALLAGE____________________________________________________________________
	@RequestMapping(value = "/emballage.form", method = RequestMethod.GET)
	public String emballageListView(ModelMap model) {
		List<Emballage> emballages = null;
		try {
			emballages = emballageService.getAllEmballages();
		}
		catch (Exception exception) {}
		// Ajouter la liste au modèle
		model.addAttribute("emballages", emballages);
		return "module/mycashier/emballage"; // Assurez-vous que cette vue existe dans le bon dossier
	}

	//____________________________AJOUT EMBALLAGE---------------------------------------------------------------------

	@RequestMapping(value = "/emballageForm.form", method = RequestMethod.GET)
	public String showAddOrUpdateEmballageForm(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
		if (id != null) {
			Emballage emballage = emballageService.getEmballageById(id);
			if (emballage!= null) {
				model.addAttribute("emballage",emballage);
			} else {
				model.addAttribute("errorMessage", "Emballage not found with ID: " + id);
			}
		} else {

		}
		return "module/mycashier/emballageForm";
	}

	@RequestMapping(value = "/emballage.form", method = RequestMethod.POST)
	public String handleAddOrUpdateEmballageForm(@RequestParam(value = "id", required = false) Integer id,
											   @RequestParam("name") String name,
											   ModelMap model) {

		try {
			Emballage emballage;
			if (id != null) {
				// Retrieve the existing Assurance
				emballage= emballageService.getEmballageById(id);
				if (emballage == null) {
					// If the assurance with the provided ID does not exist, add an error message
					model.addAttribute("errorMessage", "Emballage not found with ID: " + id);
					return "module/mycashier/emballageForm";
				}
			} else {
				// Create a new Emballage
				emballage = new Emballage();
				emballage.setUserId(Context.getAuthenticatedUser().getUserId());
			}
			// Set the fields from the form
			emballage.setName(name);
			// Save the Emballage using the service
			emballageService.saveEmballage(emballage);

			// Redirect to the list of assurances after successful addition/update
			return "redirect:/module/mycashier/emballage.form";
		}
		catch (Exception e) {
			// If there is an error, add an error message to the model
			model.addAttribute("errorMessage", "Error saving Emballage: " + e.getMessage());
			return "module/mycashier/emballageForm";
		}
	}

}
