<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Stock selon les entrepôts</title>
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

        .table-striped tbody tr {
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .table-striped tbody tr:hover {
            background-color: #e0f7fa;
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
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        function searchVenteDrug() {
            const dateDebut = $('#dateDebut').val() || new Date().toISOString().split('T')[0];
            const dateFin = $('#dateFin').val() || new Date(new Date().setDate(new Date().getDate() - 3)).toISOString().split('T')[0];
            const medicament = $('#medicament').val();
            const numeroLot = $('#numeroLot').val();
            const entrepotSourceId = $('#entrepotSourceId').val();
            const contextPath = '${pageContext.request.contextPath}';

            // Validation des dates
            if (new Date(dateDebut) > new Date(dateFin)) {
                alert("Erreur : La date de début doit être antérieure ou égale à la date de fin.");
                return;
            }

            $.ajax({
                url: contextPath + '/module/mycashier/searchVenteDrug.form',
                type: 'GET',
                data: {
                    dateDebut,
                    dateFin,
                    medicament,
                    numeroLot,
                    entrepotSourceId
                },
                success: function (data) {
                    $('#resultsTable tbody').empty();
                    data.forEach(item => {
                        const medicaments = [item.drug1, item.drug2, item.drug3].filter(Boolean).join(' ');
                        const row = `<tr data-id="${item.venteDrugId}">
                            <td>${item.dateVente}</td>
                            <td>${item.medicament}</td>
                            <td>${item.numeroLot}</td>
                            <td>${item.preparateur}</td>
                            <td>${medicaments}</td>
                            <td>${item.total}</td>
                            <td>${item.reste}</td>
                            <input type="hidden" class="venteDrugId" value="${item.venteDrugId}"/>
                        </tr>`;
                        $('#resultsTable tbody').append(row);
                    });

                    // Ajout d'un événement de clic pour chaque ligne du tableau
                    $('#resultsTable tbody tr').click(function () {
                        const venteDrugId = $(this).find('.venteDrugId').val();
                        window.location.href = contextPath + '/module/mycashier/venteProduit.form?venteDrugId=' + venteDrugId;
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

        function searchStock() {
            const params = {
                medicament: $('#medicament').val(),
                numeroLot: $('#numeroLot').val(),
                entrepotId: $('#entrepotId').val(),
                assuranceId: $('#assuranceId').val(),
                emballageId: $('#emballageId').val(),
                perimeAvant: $('#perimeAvant').val(),
                forme: $('#forme').val()
            };

            axios.get('<c:url value="/searchStockEntrepot.form"/>', { params })
                .then(response => {
                    const stockData = response.data;
                    const stockTable = $('#stockTable');
                    stockTable.empty();

                    stockData.forEach(stock => {
                        const row = `<tr>
                            <td>${stock.medicamentName || ''}</td>
                            <td>${stock.numeroLot || ''}</td>
                            <td>${stock.entrepotName || ''}</td>
                            <td>${stock.prixAssurance || ''}</td>
                            <td>${stock.quantiteStock || ''}</td>
                            <td>${stock.quantiteBoite || ''}</td>
                            <td>${stock.quantitePlaquette || ''}</td>
                            <td>${stock.quantiteUnitaire || ''}</td>
                            <td>${stock.datePeremption ? new Date(stock.datePeremption).toLocaleDateString('fr-FR') : ''}</td>
                        </tr>`;
                        stockTable.append(row);
                    });
                })
                .catch(error => {
                    console.error("Erreur lors de la recherche du stock:", error);
                });
        }
    </script>
</head>
<body>

<div class="container mt-4">
    <h2 class="mb-4">Gestion du Stock</h2>

    <!-- Formulaire de recherche -->
    <form id="searchForm" class="mb-4">
        <div class="row">
            <div class="col-md-3">
                <label for="medicament">Médicament</label>
                <input type="text" class="form-control" id="medicament" name="medicament" placeholder="Nom du médicament">
            </div>
            <div class="col-md-2">
                <label for="numeroLot">Numéro de lot</label>
                <input type="text" class="form-control" id="numeroLot" name="numeroLot" placeholder="Numéro de lot">
            </div>
            <div class="col-md-2">
                <label for="entrepotId">Entrepôt</label>
                <input type="number" class="form-control" id="entrepotId" name="entrepotId" placeholder="ID de l'entrepôt">
            </div>
            <div class="col-md-2">
                <label for="assuranceId">Assurance</label>
                <input type="number" class="form-control" id="assuranceId" name="assuranceId" placeholder="ID de l'assurance">
            </div>
            <div class="col-md-2">
                <label for="emballageId">Emballage</label>
                <input type="number" class="form-control" id="emballageId" name="emballageId" placeholder="ID de l'emballage">
            </div>
            <div class="col-md-3">
                <label for="perimeAvant">Péremption avant</label>
                <input type="date" class="form-control" id="perimeAvant" name="perimeAvant">
            </div>
            <div class="col-md-2">
                <label for="forme">Forme</label>
                <input type="text" class="form-control" id="forme" name="forme" placeholder="Forme">
            </div>
            <div class="col-md-2 mt-4">
                <button type="button" class="btn btn-primary btn-block" onclick="searchStock()">Rechercher</button>
            </div>
        </div>
    </form>

    <!-- Tableau de résultats -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Médicament</th>
            <th>Numéro de Lot</th>
            <th>Entrepôt</th>
            <th>Assurance Prix</th>
            <th>Quantité Stock</th>
            <th>Quantité Boîte</th>
            <th>Quantité Plaquette</th>
            <th>Quantité Unitaire</th>
            <th>Date de Péremption</th>
        </tr>
        </thead>
        <tbody id="stockTable">
        <!-- Les résultats de la recherche seront affichés ici -->
        </tbody>
    </table>
</div>

</body>
</html>
