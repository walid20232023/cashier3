<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>

<html lang="fr">
    <head>
        <%@include file="layouts/_head.jsp"%>
        <title>Vente de produits à ma pharmacie</title>
    </head>
    <body>

    <%@include file="layouts/_header.jsp"%>

    <div id="body-wrapper">
        <script type="text/javascript"> </script>

        <div id="error-message" class="note-container">
            <div class="note error" style="display: none">
                <div class="text">
                    <i class="icon-remove medium"></i>

                </div>
                <div class="close-icon"><i class="icon-remove"></i></div>
            </div>
        </div>
        <div id="info-message" class="note-container">
            <div class="note success" style="display: none">
                <div class="text">
                    <i class="icon-ok medium"></i>

                </div>

                <div class="close-icon"><i class="icon-remove"></i></div>
            </div>
        </div>
        <div id="content" class="container-fluid">

            <div class="row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-12 homeNotification"> </div>
            </div>
            <div class="row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-12" id="title">
                <h2><a href="${pageContext.request.contextPath}/module/mycashier/accueilPharmacie.form"> PHARMATIE </a></h2>
                </div>
            </div>
            <div class="row">
                <div class="card">
                    <div class="card-header text-center" >
                        <h1>Liste des produits de l'approvisionnement validé</h1>
                    </div>
                    <div class="card-body">
                        <form class="form-inline" onsubmit="event.preventDefault(); searchVenteDrug();">
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="startDate" class="sr-only">Date de début</label>
                                <input type="date" class="form-control" id="startDate" placeholder="Date de début">
                            </div>
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="endDate" class="sr-only">Date de fin</label>
                                <input type="date" class="form-control" id="endDate" placeholder="Date de fin">
                            </div>
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="clientNom" class="sr-only">Nom du client</label>
                                <input type="text" class="form-control" id="clientNom" placeholder="Nom du client">
                            </div>
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="clientPrenom" class="sr-only">Prénom du client</label>
                                <input type="text" class="form-control" id="clientPrenom" placeholder="Prénom du client">
                            </div>
                            <div class="form-group mx-sm-3 mb-2">
                                <label for="query" class="sr-only">Recherche</label>
                                <input type="text" class="form-control" id="query" placeholder="Recherche">
                            </div>
                            <button type="submit" class="btn btn-primary mb-2">Rechercher</button>
                        </form>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped" id="resultsTable">
                            <thead>
                            <tr>
                                <th>Date de Vente</th>
                                <th>Nom du Client</th>
                                <th>Prénom du Client</th>
                                <th>Préparateur</th>
                                <th>Médicaments</th>
                                <th>Total</th>
                                <th>Reste à payer</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Les résultats de la recherche seront ajoutés ici via JavaScript -->
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            const contextPath = '${pageContext.request.contextPath}';

            // Appeler calculateTotals une fois que la page est chargée
            $('#medicamentTableBody tr').each(function() {
                var $row = $(this);

                // Log pour la ligne actuelle
                console.log("Processing row:", $row);

                // Récupérer la quantité
                var quantity = parseFloat($row.find('input[name="quantity"]').val());
                console.log("Quantity:", quantity);

                // Récupérer le prix
                var price = parseFloat($row.find('input[name="pu"]').val());
                console.log("Price:", price);

                // Calculer le total
                var total = quantity * price;
                console.log("Total:", total);

                // Mettre à jour le champ total de la ligne
                $row.find('input[name="total"]').val(total.toFixed(2));
                console.log("Updated total field:", $row.find('input[name="total"]').val());
            });

            // Calculer les totaux globaux
            console.log("Calling calculateTotals...");
            calculateTotals();


            // Fonction de recherche de client
            $('#searchClient').on('input', function() {
                var query = $(this).val();
                if (query.length > 2) {
                    $.ajax({
                        url: contextPath + '/module/mycashier/searchClient.form',
                        method: 'GET',
                        data: { query: query },
                        success: function(response) {
                            $('#clientSearchResults').empty();
                            response.forEach(function(client) {
                                $('#clientSearchResults').append('<li class="list-group-item client-item" data-client=\'' + JSON.stringify(client) + '\'>' + client.id + ' ' + client.name + ' ' + client.firstnames + '</li>');
                            });
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error('Erreur lors de la récupération des clients:', textStatus, errorThrown);
                        }
                    });
                }
            });

            // Sélection d'un client
            $('#clientSearchResults').on('click', '.client-item', function() {
                var client = $(this).data('client');
                $('#client').val(client.id.toString());
                $('#nom').val(client.name);
                $('#prenom').val(client.firstnames);
                $('#dateNaissance').val(client.birthDate ? new Date(client.birthDate).toISOString().split('T')[0] : '');
                $('#sexe').val(client.sex === 'M' ? 'Masculin' : client.sex === 'F' ? 'Féminin' : '');
                $('#assurance1').val(client.assurance1 || '');
                $('#assurance2').val(client.assurance2 || '');


                $('#clientSearchResults').empty();
            });

            //Envoi id client
            document.addEventListener('DOMContentLoaded', function() {
                // Exemple de gestion des résultats de recherche des clients
                document.getElementById('clientSearchResults').addEventListener('click', function(event) {
                    if (event.target && event.target.matches('li')) {
                        const clientId = event.target.getAttribute('data-client-id');
                        const clientNom = event.target.getAttribute('data-client-nom');

                        // Mettre à jour les champs du formulaire avec les informations du client sélectionné
                        document.getElementById('clientId').value = clientId;
                        document.getElementById('client').value = clientId;
                        document.getElementById('nom').value = clientNom;

                    }
                });
            });

            // Gestion des cases à cocher pour qu'une seule soit cochée à la fois
            $('#assurance1Checkbox').on('change', function() {
                if (this.checked) {
                    $('#assurance2Checkbox').prop('checked', false);
                    // Récupère dynamiquement la valeur de l'assurance 1
                    var assurance1Value = $('#assurance1').val();
                    $('#assuranceSelectionneeValue').val(assurance1Value);
                } else {
                    $('#assuranceSelectionneeValue').val(''); // Réinitialiser si la case est décochée
                }
            });

            $('#assurance2Checkbox').on('change', function() {
                if (this.checked) {
                    $('#assurance1Checkbox').prop('checked', false);
                    // Récupère dynamiquement la valeur de l'assurance 2
                    var assurance2Value = $('#assurance2').val();
                    $('#assuranceSelectionneeValue').val(assurance2Value);
                } else {
                    $('#assuranceSelectionneeValue').val(''); // Réinitialiser si la case est décochée
                }
            });

            // Optionnel : Réinitialiser le champ caché lors de la soumission si aucune case n'est cochée
            $('form').on('submit', function() {
                if (!$('#assurance1Checkbox').is(':checked') && !$('#assurance2Checkbox').is(':checked')) {
                    $('#assuranceSelectionneeValue').val('');
                }
            });


            // Redirection vers la page d'édition du client
            $('#editClient').on('click', function() {
                var clientId = $('#client').val();
                if (clientId) {
                    window.location.href = contextPath + '/module/mycashier/client.form?id=' + clientId;
                }
            });

            // Fonction de recherche de médicament
            $('#searchDrug').on('input', function() {
                var query = $(this).val();
                if (query.length > 2) {
                    $.ajax({
                        url: contextPath + '/module/mycashier/searchDrug.form',
                        method: 'GET',
                        data: { query: query },
                        success: function(response) {
                            $('#drugSearchResults').empty();
                            response.forEach(function(drug) {
                                $('#drugSearchResults').append('<li class="list-group-item drug-item" data-drug=\'' + JSON.stringify(drug) + '\'>' + drug.name + ' (' + drug.dci + ')</li>');
                            });
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error('Erreur lors de la récupération des médicaments:', textStatus, errorThrown);
                        }
                    });
                }
            });

            // Désactiver l'historique de saisie automatique pour le champ de recherche
            $('#searchDrug').attr('autocomplete', 'off');

            // Effacer le contenu du champ de recherche lors du focus
            $('#searchDrug').on('focus', function() {
                $(this).val('');
            });




            // Sélection d'un médicament
            $('#drugSearchResults').on('click', '.drug-item', function() {
                var drug = $(this).data('drug');
                console.log('Voici la Drug ID:', drug.id); // Vérifier l id du medicament
                var $newRow = $('<tr></tr>');

                $newRow.append('<td><input type="text" class="form-control" name="drugName" value="' + drug.name + '" readonly></td>');
                $newRow.append('<td><input type="number" class="form-control quantity" name="quantity" value="1"></td>');
                $newRow.append('<td><input type="number" class="form-control" name="pu" value="' + drug.price + '" readonly></td>');
                $newRow.append('<td><input type="number" class="form-control" name="Inam" value="' + drug.baseInam + '" readonly></td>');
                $newRow.append('<td><input type="number" class="form-control total" name="total" value="' + drug.price + '" readonly></td>');
                $newRow.append('<td><input type="text" class="form-control" name="stockPh" value="' + drug.stockLocal + '" readonly></td>');
                $newRow.append('<td><input type="text" class="form-control" name="stockMg" value="' + drug.stockMag + '" readonly></td>');
                $newRow.append('<td><i class="bi bi-eye icon-view"></i> <i class="bi bi-trash icon-delete"></i></td>');
                $newRow.append('<td><input type="hidden" name="medicamentIds" value="' + drug.id + '"></td>');

                $('#medicamentTableBody').append($newRow);
                $('#drugSearchResults').empty();
                $('#searchDrug').val('');
                calculateTotals(); // Recalculer les totaux après ajout d'une nouvelle ligne
            });


            // Calculer le total lorsque la quantité est modifiée
            $('#medicamentTableBody').on('input', 'input[name="quantity"]', function() {
                var $row = $(this).closest('tr');
                var quantity = parseFloat($(this).val());
                var price = parseFloat($row.find('input[name="pu"]').val());
                var total = quantity * price;
                $row.find('input[name="total"]').val(total.toFixed(2));
                calculateTotals(); // Recalculer les totaux après modification de la quantité
            });

            function calculateTotals() {
                // Réinitialiser la somme à 0 avant chaque calcul
                let sum = 0;
                let partAssurance = 0;
                let partClient = 0;

                // Récupérer uniquement les champs 'total' visibles dans les lignes de médicaments
                const totalFields = $('#medicamentTableBody input[name="total"]:visible');

                // Calculer la somme totale des champs 'total'
                totalFields.each(function() {
                    sum += parseFloat($(this).val()) || 0;
                });

                const inamChecked = $('#assurance1Checkbox').is(':checked');
                const gtaChecked = $('#assurance2Checkbox').is(':checked');

                if (inamChecked) {
                    // Réinitialiser la base INAM avant chaque calcul
                    let baseInamTotal = 0;

                    // Récupérer les champs 'Inam' visibles dans les lignes de médicaments
                    $('#medicamentTableBody input[name="Inam"]:visible').each(function() {
                        baseInamTotal += parseFloat($(this).val()) || 0;
                    });

                    // Calculer Part Assurance comme 80% de la base INAM
                    partAssurance = baseInamTotal * 0.8;
                } else if (gtaChecked) {
                    // Calculer Part Assurance comme 80% de la somme totale
                    partAssurance = sum * 0.8;
                }

                // Calculer Part Client
                partClient = sum - partAssurance;

                // Mettre à jour les champs correspondants
                $('#partAssurance').val(partAssurance.toFixed(2));
                $('#partClient').val(partClient.toFixed(2));
                $('#total').val(sum.toFixed(2));
            }

            // Attacher les événements de changement pour recalculer les totaux
            $('#assurance1Checkbox, #assurance2Checkbox').on('change', calculateTotals);
            $('#medicamentTableBody').on('input', 'input[name="quantity"], input[name="total"], input[name="Inam"]', calculateTotals);

            // Initialiser les valeurs au chargement
            calculateTotals();


            // Supprimer une ligne de médicament
            $('#medicamentTableBody').on('click', '.icon-delete', function() {
                $(this).closest('tr').remove();
                calculateTotals(); // Recalculer les totaux après suppression d'une ligne
            });
        });


        document.getElementById('venteDrugForm').addEventListener('submit', function(event) {
            event.preventDefault(); // Empêche la soumission réelle pour inspecter les données
            var formData = new FormData(this);

            // Convertir les données en un objet clé-valeur pour affichage
            var data = {};
            formData.forEach(function(value, key) {
                if (data[key]) {
                    // Si le champ est déjà présent, transformez-le en tableau
                    if (!Array.isArray(data[key])) {
                        data[key] = [data[key]];
                    }
                    data[key].push(value);
                } else {
                    data[key] = value;
                }
            });

            console.log('Données envoyées au serveur :', data);

            // Vous pouvez également afficher les données dans le DOM si nécessaire
            var displayDiv = document.createElement('div');
            displayDiv.textContent = JSON.stringify(data, null, 2);
            document.body.appendChild(displayDiv);

            // Soumettez le formulaire après inspection
            this.submit(); // Décommentez pour permettre la soumission réelle
        });

        //  Au chargement de la table
        document.addEventListener('DOMContentLoaded', function() {
            const btnValider = document.getElementById('btnValider');
            const validateField = document.getElementById('validate');
            const venteDrugId = document.getElementById('venteDrugId').value;
            const formElements = document.querySelectorAll('#venteDrugForm input, #venteDrugForm select');

            // Fonction pour gérer le style du bouton en fonction de l'état activé/désactivé
            function toggleButtonState(isEnabled) {
                if (isEnabled) {
                    btnValider.disabled = false;
                    btnValider.classList.remove('btn-disabled');
                    btnValider.classList.add('btn-enabled');
                } else {
                    btnValider.disabled = true;
                    btnValider.classList.remove('btn-enabled');
                    btnValider.classList.add('btn-disabled');
                }
            }

            // Si la page est rechargée (ou actualisée)
            if (performance.navigation.type === 1) {
                toggleButtonState(false);  // Désactiver le bouton "Valider" et appliquer le style désactivé lors de l'actualisation
            } else {
                // Si venteDrugId est présent, activer le bouton Valider
                if (venteDrugId) {
                    toggleButtonState(true);
                }
            }

            // Désactiver le bouton "Valider" dès qu'il y a une modification dans le formulaire
            formElements.forEach(function(element) {
                element.addEventListener('change', function() {
                    toggleButtonState(false); // Désactiver Valider si une modification est détectée
                });
            });

            // Modifier la valeur du champ caché 'validate' à 1 lorsque le bouton Valider est cliqué
            btnValider.addEventListener('click', function(event) {
                validateField.value = 1;
            });
        });


    </script>
    </body>
</html>