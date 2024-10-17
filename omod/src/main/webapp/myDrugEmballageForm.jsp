<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire de Vente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css">
    <style>
        /* Styles personnalisés */
        body {
            background-color: #f4f4f9;
        }
        .navbar {
            background-color: #29AB87;
        }
        .navbar-dark .navbar-nav .nav-link {
            color: #ffffff;
        }
        .navbar-dark .navbar-nav .nav-link:hover {
            color: #e8f5e9;
        }
        .card {
            margin: 20px 0;
        }
        .card-header {
            background-color: #29AB87;
            color: #ffffff;
        }
        .btn-custom {
            background-color: #29AB87;
            color: #ffffff;
        }
        .btn-custom:hover {
            background-color: #248739;
        }
        .icon-view, .icon-delete, .icon-edit {
            cursor: pointer;
            color: #29AB87;
        }
        .icon-view:hover, .icon-delete:hover, .icon-edit:hover {
            color: #248739;
        }
        .form-table th, .form-table td {
            text-align: center;
            vertical-align: middle;
        }
        .form-table th {
            background-color: #e8f5e9;
        }
        .medicament-col {
            width: 30%; /* Augmenter la largeur pour le champ Médicament */
        }
        .quantity-col, .pu-col, .total-col, .stock-ph-col, .stock-mg-col {
            width: 10%;
        }
        .form-table td, .form-table th {
            white-space: nowrap;
        }
        /* Style pour les listes déroulantes */
        #clientSearchResults, #drugSearchResults {
            position: absolute;
            width: 100%;
            max-height: 200px;
            overflow-y: auto;
            z-index: 1000;
            background-color: white;
            border: 1px solid #ccc;
        }
        .list-group-item {
            cursor: pointer;
        }
        .list-group-item:hover {
            background-color: #f0f0f0;
        }
        /* Style spécifique pour les champs d'assurance */
        .form-group.assurance input {
            background-color: #e0f7fa; /* Bleu clair */
            border: 1px solid #b2ebf2; /* Bordure légèrement plus foncée */
            border-radius: 4px; /* Coins légèrement arrondis */
        }
        .form-group.assurance {
            border: 1px solid #b2ebf2; /* Bordure autour du champ */
            border-radius: 4px; /* Coins légèrement arrondis */
            padding: 10px; /* Espacement intérieur */
            margin-bottom: 10px; /* Espacement entre les champs */
        }

        #partAssurance, #partClient, #total {
            font-weight: bold;
            color: #2c3e50;
            background-color: #ecf0f1;
            text-align: right;
        }

        button.btn-success {
            background-color: #27ae60;
            border-color: #27ae60;
        }

     #partAssurance, #partClient, #total {
         font-weight: bold;
         color: #2c3e50;
         background-color: #ecf0f1;
         text-align: right;
         border-radius: 6px; /* Ajout de bordures arrondies */
         padding: 8px; /* Ajout d'un peu d'espace intérieur */
         box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Ombre légère pour l'effet de relief */
     }

     button.btn-success {
         background-color: #27ae60;
         border-color: #27ae60;
         border-radius: 8px; /* Bordures arrondies */
         padding: 10px 20px; /* Augmentation de la taille du bouton */
         transition: background-color 0.3s ease, transform 0.3s ease; /* Transition douce */
     }

     button.btn-success:hover {
         background-color: #229954; /* Changement de couleur au survol */
         transform: scale(1.05); /* Léger zoom au survol */
     }

     /* Styles pour les champs de texte */
     .custom-input {
         font-weight: bold;
         color: #34495e; /* Couleur bleu-gris */
         background-color: #eaf2f8; /* Bleu très clair */
         border: 2px solid #5dade2; /* Bordure bleu moyen */
         border-radius: 8px; /* Bordures arrondies */
         text-align: right;
         padding: 10px; /* Espace intérieur pour plus de confort */
         box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1); /* Ombre intérieure pour un effet enfoncé */
     }

     /* Style pour le bouton */
     .custom-button {
         font-size: 1rem; /* Taille du texte réduite */
         padding: 8px 16px; /* Taille ajustée pour un bouton plus petit */
         background-color: #3498db; /* Bleu vif */
         border-color: #2980b9; /* Bleu foncé */
         border-radius: 8px; /* Bordures arrondies */
         transition: background-color 0.3s ease, transform 0.3s ease; /* Transition douce */
         align-self: center; /* Alignement centré verticalement */
     }

     .custom-button:hover {
         background-color: #2980b9; /* Couleur de survol */
         transform: scale(1.05); /* Léger zoom au survol */
     }

     /* Bouton Valider désactivé (gris clair) */
     .btn-disabled {
         background-color: lightgray;
         color: #fff;
         cursor: not-allowed;
         border-color: lightgray;
     }

     /* Bouton Valider activé (rouge vif) */
     .btn-enabled {
         background-color: red;
         color: #fff;
         border-color: red;
     }





    </style>
</head>
<body>
    <div class="container mt-5">
        <h2>Recherche de Stock Entrepôt</h2>

        <!-- Formulaire de recherche -->
        <form id="searchForm" class="form-inline">
            <div class="form-group mb-2">
                <label for="medicament" class="sr-only">Médicament</label>
                <input type="text" class="form-control" id="medicament" placeholder="Nom du médicament">
            </div>
            <div class="form-group mb-2 mx-sm-3">
                <label for="emballage" class="sr-only">Emballage</label>
                <input type="text" class="form-control" id="emballage" placeholder="Type d'emballage">
            </div>
            <div class="form-group mb-2">
                <label for="forme" class="sr-only">Forme</label>
                <input type="text" class="form-control" id="forme" placeholder="Forme">
            </div>
            <div class="form-group mb-2 mx-sm-3">
                <label for="assurance" class="sr-only">Assurance</label>
                <input type="text" class="form-control" id="assurance" placeholder="Assurance">
            </div>
            <button type="button" class="btn btn-primary mb-2" onclick="searchStock()">Rechercher</button>
        </form>

        <!-- Tableau de résultats -->
        <table class="table table-striped mt-4" id="resultsTable">
            <thead>
                <tr>
                    <th>Nom du Médicament</th>
                    <th>Emballage</th>
                    <th>Forme</th>
                    <th>Prix Assurance</th>
                    <th>Quantité</th>
                    <th>Date de Péremption</th>
                    <th>Numéro de Lot</th>
                </tr>
            </thead>
            <tbody id="resultsBody">
                <!-- Les résultats de la recherche apparaîtront ici -->
            </tbody>
        </table>
    </div>

    <!-- Script AJAX pour la recherche -->
    <script type="text/javascript">
        function searchStock() {
            // Récupération des valeurs du formulaire
            var medicament = $('#medicament').val();
            var emballage = $('#emballage').val();
            var forme = $('#forme').val();
            var assurance = $('#assurance').val();

            // Requête AJAX
            $.ajax({
                url: '<c:url value="/searchPaymentDrug.form"/>',
                type: 'GET',
                dataType: 'json',
                data: {
                    medicament: medicament,
                    emballage: emballage,
                    forme: forme,
                    assurance: assurance
                },
                success: function(responseList) {
                    // Nettoyage du tableau des résultats
                    $('#resultsBody').empty();

                    // Remplissage du tableau avec les données reçues
                    $.each(responseList, function(index, item) {
                        var row = '<tr>' +
                            '<td>' + item.medicament + '</td>' +
                            '<td>' + item.emballage + '</td>' +
                            '<td>' + item.forme + '</td>' +
                            '<td>' + item.prixAssurance + '</td>' +
                            '<td>' + item.quantityEmballage + '</td>' +
                            '<td>' + item.datePeremption + '</td>' +
                            '<td>' + item.numeroLot + '</td>' +
                        '</tr>';
                        $('#resultsBody').append(row);
                    });
                },
                error: function(xhr, status, error) {
                    console.error('Erreur de recherche:', error);
                }
            });
        }
    </script>
</body>
</html>