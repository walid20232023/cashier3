<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des médicaments</title>
    <!-- Intégration de Bootstrap et d'un style personnalisé -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f0f8f8;
        }

        h1 {
            color: #006d5b;
            text-align: center;
            margin-top: 20px;
        }

        .form-inline {
            justify-content: center;
            margin-bottom: 30px;
        }

        .form-control {
            border-radius: 5px;
            border: 1px solid #005f6b;
        }

        .btn-primary {
            background-color: #00796b;
            border-color: #004d40;
        }

        .btn-primary:hover {
            background-color: #004d40;
        }

        /* Style du tableau avec des couleurs */
        .table-striped tbody tr {
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f0f4c3; /* Lignes vert pâle */
        }

        .table-striped tbody tr:nth-of-type(even) {
            background-color: #ffe0b2; /* Lignes orange pâle */
        }

        .table-striped tbody tr:hover {
            background-color: #ffd54f; /* Survol jaune clair */
        }

        .table thead th {
            background-color: #00796b;
            color: white;
        }

        .table-striped tbody tr td {
            vertical-align: middle;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // Fonction pour formater les dates
        function formatDate(dateString) {
            var options = { year: 'numeric', month: '2-digit', day: '2-digit' };
            return new Date(dateString).toLocaleDateString('fr-FR', options);
        }

        // Fonction pour rechercher les ventes de médicaments
        function searchVenteDrug() {
            var startDate = $('#startDate').val();
            var endDate = $('#endDate').val();
            var clientNom = $('#clientNom').val();
            var clientPrenom = $('#clientPrenom').val();
            var query = $('#query').val();
            const contextPath = '${pageContext.request.contextPath}';

            // Gestion des dates par défaut
            if (!startDate) {
                startDate = new Date().toISOString().split('T')[0];
                $('#startDate').val(startDate);
            }

            if (!endDate) {
                let dateMinus3 = new Date();
                dateMinus3.setDate(dateMinus3.getDate() - 3);
                endDate = dateMinus3.toISOString().split('T')[0];
                $('#endDate').val(endDate);
            }

            // Validation des dates
            var start = new Date(startDate);
            var end = new Date(endDate);
            if (start > end) {
                alert("Erreur : La date de début doit être antérieure ou égale à la date de fin.");
                return;
            }

            // Requête AJAX pour chercher les ventes
            $.ajax({
                url: contextPath + '/module/mycashier/searchVenteDrug.form',
                type: 'GET',
                data: {
                    startDate: startDate,
                    endDate: endDate,
                    clientNom: clientNom,
                    clientPrenom: clientPrenom,
                    query: query,
                    validate: 1 // Ajout de la variable 'validate' avec la valeur 1 (true)
                },
                success: function (data) {
                    $('#resultsTable tbody').empty();
                    data.forEach(function (item) {
                        var medicaments = [item.drug1, item.drug2, item.drug3].filter(Boolean).join(' ');

                        var row = '<tr data-id="' + item.venteDrugId + '">' +
                            '<td>' + formatDate(item.dateVente) + '</td>' + // Formatage de la date
                            '<td>' + item.clientNom + '</td>' +
                            '<td>' + item.clientPrenom + '</td>' +
                            '<td>' + item.preparateur + '</td>' +
                            '<td>' + medicaments + '</td>' +
                            '<td>' + item.total + '</td>' +
                            '<td>' + item.reste + '</td>' +
                            '<input type="hidden" class="venteDrugId" value="' + item.venteDrugId + '"/>' +
                            '</tr>';
                        $('#resultsTable tbody').append(row);
                    });

                    // Ajout d'un événement de clic pour chaque ligne du tableau
                    $('#resultsTable tbody tr').click(function () {
                        var venteDrugId = $(this).find('.venteDrugId').val();
                        window.location.href = contextPath + '/module/mycashier/paymentDrug.form?venteDrugId=' + venteDrugId;
                    });
                },
                error: function (xhr, status, error) {
                    console.error("Erreur lors de la requête : ", error);
                }
            });
        }

        $(document).ready(function () {
            searchVenteDrug(); // Lancer la recherche par défaut lors du chargement de la page
        });
    </script>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Liste My Drug Emballage</h2>

    <!-- Formulaire de recherche -->
    <div class="card p-4 mb-4">
        <form id="searchForm" class="form-inline">
            <input type="text" class="form-control mr-2" id="clientNom" placeholder="Nom du client">
            <input type="text" class="form-control mr-2" id="clientPrenom" placeholder="Prénom du client">
            <input type="text" class="form-control mr-2" id="startDate" placeholder="Date de début (AAAA-MM-JJ)">
            <input type="text" class="form-control mr-2" id="endDate" placeholder="Date de fin (AAAA-MM-JJ)">
            <button type="button" class="btn btn-primary" onclick="searchVenteDrug()">Rechercher</button>
        </form>
    </div>

    <!-- Tableau pour afficher les résultats -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Date de Vente</th>
                <th>Nom du Client</th>
                <th>Prénom du Client</th>
                <th>Préparateur</th>
                <th>Médicaments</th>
                <th>Total</th>
                <th>Reste</th>
            </tr>
        </thead>
        <tbody id="resultsTable">
            <!-- Les résultats de recherche apparaîtront ici -->
        </tbody>
    </table>
</div>

<script>
    function searchStockEntrepot() {
        const searchParams = $("#searchForm").serialize();

        $.ajax({
            url: "${pageContext.request.contextPath}/searchStockEntrepot.form",
            type: "GET",
            data: searchParams,
            dataType: "json",
            success: function(response) {
                $("#resultsTable").empty(); // Vide le tableau avant d'ajouter les nouveaux résultats

                if (response.length > 0) {
                    $.each(response, function(index, item) {
                        $("#resultsTable").append(`
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.medicament}</td>
                                <td>${item.numeroLot}</td>
                                <td>${item.datePeremption ? formatDate(item.datePeremption) : 'N/A'}</td>
                                <td>${item.prixAssurance ? item.prixAssurance : 'N/A'}</td>
                                <td>${item.quantiteStock}</td>
                                <td>${item.quantiteBoite}</td>
                                <td>${item.quantitePlaquette}</td>
                                <td>${item.quantiteUnitaire}</td>
                            </tr>
                        `);
                    });
                } else {
                    $("#resultsTable").append('<tr><td colspan="9" class="text-center">Aucun résultat trouvé</td></tr>');
                }
            },
            error: function(xhr, status, error) {
                console.error("Erreur lors de la recherche:", error);
            }
        });
    }
</script>

</body>
</html>
