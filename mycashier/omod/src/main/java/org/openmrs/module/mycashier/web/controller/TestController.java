package org.openmrs.module.mycashier.web.controller;

import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.api.AssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module/mycashier")
public class TestController {
	
	@Autowired
	private AssuranceService assuranceService;
	
	@RequestMapping(value = "/test.form")
	public String testView() {
		// Créer une instance d'assurance
		Assurance myAssurance = new Assurance();
		// Appel du service
		myAssurance.setName("Assurance test");
		try {
			assuranceService.saveAssurance(myAssurance);
		}
		catch (Exception exception) {
			System.out.println("Le service n'a pas marché");
		}
		return "module/mycashier/test"; // Assurez-vous que cette vue existe dans le bon dossier
	}
}
