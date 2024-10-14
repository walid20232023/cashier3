package org.openmrs.module.mycashier.web.controller;


import org.openmrs.module.mycashier.*;
import org.openmrs.module.mycashier.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/module/mycashier")
public class ApprovisController {

    @Autowired
    private ApprovisionnementService approvisionnementService;

    @Autowired
    private VenteDrugService venteDrugService;

    @Autowired
    private MyDrugService myDrugService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private EntrepotService entrepotService;


    //______________________________AFFICHAGE ACTIONS APPROVISIONNMENTS____________________________________________________________________
    @RequestMapping(value = "/actionsApprovis.form", method = RequestMethod.GET)
    public String afficheActionsApprovis(ModelMap model) {

        return "module/mycashier/actionsApprovis"; // Assurez-vous que cette vue existe dans le bon dossier
    }



    //______________________________AFFICHAGE FORMULAIRE APPROVISIONNMENTS_____________________________________________________
    @RequestMapping(value = "/approvisForm.form", method = RequestMethod.GET)
    public String afficheFormApprovis(@RequestParam(value = "id", required = false) Integer approvisionnementId,
                                      Model model) {

        if (approvisionnementId != null) {
            // Récupération de l'approvisionnement
            Approvisionnement approvisionnement = approvisionnementService.getApprovisionnementById(approvisionnementId);

            if (approvisionnement != null) {
                // Ajout de la date d'approvisionnement au modèle
                model.addAttribute("dateApprovisionnement", approvisionnement.getDateTimeApprovisionnement());

                // Ajout du nom de l'agent émetteur et receveur au modèle
                model.addAttribute("nomEmetteur", approvisionnement.getAgentEmetteur().getUsername());
                model.addAttribute("nomReceveur", approvisionnement.getAgentReceveur().getUsername());

                // Ajout des noms des entrepôts source et cible au modèle
                model.addAttribute("entrepotSource", approvisionnement.getEntrepotSource().getName());
                model.addAttribute("entrepotCible", approvisionnement.getEntrepotCible().getName());

                // Récupération des listes des IDs et quantités des MyDrugEmballages associés
                List<Integer> myDrugEmballageIds = approvisionnementService.getMyDrugEmballageIdsByApprovisionnementId(approvisionnementId);
                List<Integer> quantites = approvisionnementService.getQuantitesByApprovisionnementId(approvisionnementId);

                // Transformation des MyDrugEmballage en DTO et ajout au modèle
                List<MyDrugEmballageDTO> myDrugEmballageDTOs = new ArrayList<>();
                for (int i = 0; i < myDrugEmballageIds.size(); i++) {
                    MyDrugEmballage myDrugEmballage = myDrugService.getMyDrugEmballageById(myDrugEmballageIds.get(i))  ;
                    MyDrugEmballageDTO dto = MyDrugEmballageDTO.convertToDTO(myDrugEmballage);

                    //Récupérer le stock pour cet entrepot
                    Integer stockEntrepot = entrepotService.getStockByMyDrugEmballage( myDrugEmballage.getId(),
                                                                                       approvisionnement.getEntrepotSource().getId())  ;

                    //Affecter le stock et la quantité à approvisionner au dto
                    dto.setQuantityStock(stockEntrepot);
                    dto.setQuantityApprovis( quantites.get(i));

                    //Ajout du dto
                    myDrugEmballageDTOs.add(dto);
                }
                model.addAttribute("myDrugEmballageDTOs", myDrugEmballageDTOs);
            }
        }

        return "module/mycashier/approvisForm";
    }


//______________________________SAUVEGARDE FORMULAIRE APPROVISIONNEMENTS_____________________________________________________
@RequestMapping(value = "/saveApprovis.form", method = RequestMethod.POST)
public String saveApprovisionnement(
    @RequestParam(value = "id", required = false) Integer approvisionnementId,
    @RequestParam("entrepotSourceId") Integer entrepotSourceId,
    @RequestParam("entrepotCibleId") Integer entrepotCibleId,
    @RequestParam("agentEmetteurId") Integer agentEmetteurId,
    @RequestParam(value ="agentReceveurId" , required = false) Integer agentReceveurId,
    @RequestParam("myDrugEmballageIds") List<Integer> myDrugEmballageIds,
    @RequestParam("quantites") List<Integer> quantites,
    @RequestParam("numero_lot") List<String> numerosLots,
    @RequestParam("numero_lot") List<String> datesPeremption,
    @RequestParam("emetteur_has_validated") Integer emetteurHasValidated,
    @RequestParam(value ="receveur_has_validated" , required = false) Integer receveurHasValidated,

    Model model) {
        // Récupérer ou créer un objet Approvisionnement
        Approvisionnement approvisionnement;
        if (approvisionnementId != null) {
            approvisionnement = approvisionnementService.getApprovisionnementById(approvisionnementId);
            if (approvisionnement == null) {
                // Gérer le cas où l'approvisionnement n'existe pas
                model.addAttribute("error", "L'approvisionnement spécifié est introuvable.");
                return "module/mycashier/approvisForm";
            }
        } else {
            approvisionnement = new Approvisionnement();

        }

        // Mise à jour des informations de base de l'approvisionnement

        approvisionnement.setEntrepotSource(entrepotService.getEntrepotById(entrepotSourceId));
        approvisionnement.setEntrepotCible(entrepotService.getEntrepotById(entrepotCibleId));
        approvisionnement.setAgentEmetteur(agentService.getAgentById(agentEmetteurId));
        approvisionnement.setAgentReceveur(agentService.getAgentById(agentReceveurId));
        approvisionnement.setDateTimeApprovisionnement(LocalDateTime.now());
        approvisionnement.setEmetteurHasValidated(emetteurHasValidated);
       if (receveurHasValidated.equals(1)) {
        approvisionnement.setReceveurHasValidated(receveurHasValidated);
       }

      // Enregistrer l'approvisionnement pour obtenir l'ID si c'est un nouvel enregistrement
        approvisionnementService.saveApprovisionnement(approvisionnement);

       //Supprimer les lignes d'approvisionnment existantes
       approvisionnementService.deleteAllLignesApprovis (approvisionnementId);


        // Traitement des MyDrugEmballage et quantités
        List<MyDrugEmballageDTO> myDrugEmballageDTOs = new ArrayList<>();
        for (int i = 0; i < myDrugEmballageIds.size(); i++) {
            MyDrugEmballage myDrugEmballage = myDrugService.getMyDrugEmballageById(myDrugEmballageIds.get(i));

            MyDrugEmballageDTO dto = MyDrugEmballageDTO.convertToDTO(myDrugEmballage);

            Integer quantiteSaisie = quantites.get(i) ;
            System.out.println("Quantité saisie :" + quantiteSaisie);

            // Définir le stock et la quantité pour approvisionnement

           Integer stockEntrepot = entrepotService.getStockByMyDrugEmballage(myDrugEmballage.getId(), entrepotSourceId);
           // dto.setQuantityStock(stockEntrepot);
            dto.setQuantityApprovis(quantites.get(i));

            // Récuperer les StockEntrepots
          StockEntrepot stockEntrepotSource =   entrepotService.getStockEntrepotByDrugEmballageAndEntrepot(
                  myDrugEmballage.getId() , entrepotSourceId , numerosLots.get(i))  ;
          StockEntrepot stockEntrepotCible =   entrepotService.getStockEntrepotByDrugEmballageAndEntrepot(
                    myDrugEmballage.getId() , entrepotCibleId , numerosLots.get(i))  ;

          //Actualiser les quantités
         stockEntrepotSource.setQuantiteStock(stockEntrepot - quantiteSaisie);
         stockEntrepotCible.setQuantiteStock(stockEntrepot + quantiteSaisie);

         //Affectation date de permemption et numero de lot
            dto.setDatePeremption(LocalDate.parse(datesPeremption.get(i),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd") )) ;
            dto.setNumeroLot(numerosLots.get(i));
         //
            // Ajout du DTO pour suivi
            myDrugEmballageDTOs.add(dto);
        }

        // Ajouter les DTO au modèle pour confirmation
        model.addAttribute("myDrugEmballageDTOs", myDrugEmballageDTOs);
        model.addAttribute("success", "L'approvisionnement a été sauvegardé avec succès.");

        return "module/mycashier/approvisConfirmation";
    }

}

