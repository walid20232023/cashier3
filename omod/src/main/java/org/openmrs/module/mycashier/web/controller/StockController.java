package org.openmrs.module.mycashier.web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class StockController {
	
	//-------------------AFFICHAGE Stock-------------------------------------------------------------
	
	@RequestMapping(value = "/displayStock.form", method = RequestMethod.GET)
	public String displayStock(ModelMap model) {
		// Vous pouvez ajouter des attributs au modèle ici si nécessaire pour la vue
		
		return "module/mycashier/displayStock";
	}
}
