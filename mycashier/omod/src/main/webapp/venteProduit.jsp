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
        .icon-view, .icon-delete {
            cursor: pointer;
            color: #29AB87;
        }
        .icon-view:hover, .icon-delete:hover {
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
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h2>Formulaire de la vente</h2>
                </div>
                <div class="card-body">
                    <form method="post">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label for="searchClient">Rechercher Client</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="searchClient" placeholder="Rechercher par ID, Nom, Prénom" autocomplete="off">
                                   <div class="input-group-append">
                                       <a href="${pageContext.request.contextPath}/module/mycashier/client.form" class="btn btn-outline-secondary" id="addClient">
                                           <i class="bi bi-plus-circle icon-plus"></i> Ajouter Client
                                       </a>
                                   </div>

                                </div>
                                <ul class="list-group" id="clientSearchResults"></ul>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="client">Code Client</label>
                                <input type="text" class="form-control" id="client" name="client" readonly>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="nom">Nom</label>
                                <input type="text" class="form-control" id="nom" name="nom" readonly>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="prenom">Prénom</label>
                                <input type="text" class="form-control" id="prenom" name="prenom" readonly>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="dateNaissance">Date de naissance</label>
                                <input type="text" class="form-control" id="dateNaissance" name="dateNaissance" readonly>
                            </div>
                        </div>

                        <hr>
                        <h3>Médicaments</h3>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label for="searchDrug">Rechercher Médicament</label>
                                <input type="text" class="form-control" id="searchDrug" placeholder="Rechercher par Nom, DCI, Groupe thérapeutique">
                                <ul class="list-group" id="drugSearchResults"></ul>
                            </div>
                        </div>
                        <table class="table table-bordered form-table">
                            <thead>
                                <tr>
                                    <th class="medicament-col">Médicament</th>
                                    <th class="quantity-col">Quantité</th>
                                    <th class="pu-col">P.U</th>
                                    <th class="pu-col">Inam</th>
                                    <th class="total-col">Total</th>
                                    <th class="stock-ph-col">Stock PH</th>
                                    <th class="stock-mg-col">Stock MG</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody id="medicamentTableBody">
                                <!-- Les lignes de médicament apparaîtront ici -->
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-custom">Enregistrer la vente</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.min.js"></script>
<script>
$(document).ready(function() {
    const contextPath = '${pageContext.request.contextPath}';

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
        var birthDate = new Date(client.birthDate);
        var formattedDate = birthDate.toISOString().split('T')[0];
        $('#nom').val(client.name);
        $('#prenom').val(client.firstnames);
        $('#dateNaissance').val(formattedDate);
        $('#clientSearchResults').empty();
        $('#searchClient').val('');
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

      // Sélection d'un médicament
        $('#drugSearchResults').on('click', '.drug-item', function() {
            var drug = $(this).data('drug');
            var $newRow = $('<tr></tr>');

            $newRow.append('<td><input type="text" class="form-control" name="drugName" value="' + drug.name + '" readonly></td>');
            $newRow.append('<td><input type="number" class="form-control quantity" name="quantity" value="1"></td>');
            $newRow.append('<td><input type="text" class="form-control" name="pu" value="' + drug.price+ '" readonly></td>');
            $newRow.append('<td><input type="text" class="form-control" name="Inam" value="' + drug.baseInam+ '" readonly></td>');
            $newRow.append('<td><input type="text" class="form-control total" name="total" value="' + drug.price+ '" readonly></td>');
            $newRow.append('<td><input type="text" class="form-control" name="stockPh" value="' + drug.stockLocal + '" readonly></td>');
            $newRow.append('<td><input type="text" class="form-control" name="stockMg" value="' + drug.stockMag + '" readonly></td>');
            $newRow.append('<td><i class="bi bi-eye icon-view"></i> <i class="bi bi-trash icon-delete"></i></td>');

            $('#medicamentTableBody').append($newRow);
            $('#drugSearchResults').empty();
            $('#searchDrug').val('');
        });

    // Calculer le total lorsque la quantité est modifiée
    $('#medicamentTableBody').on('input', 'input[name="quantity"]', function() {
        var $row = $(this).closest('tr');
        var quantity = parseFloat($(this).val());
        var price = parseFloat($row.find('.pu-col').eq(0).text());
        var total = quantity * price;
        $row.find('.total-col').text(total.toFixed(2));
    });

    // Supprimer une ligne de médicament
    $('#medicamentTableBody').on('click', '.icon-delete', function() {
        $(this).closest('tr').remove();
    });
});
</script>
</body>
</html>
