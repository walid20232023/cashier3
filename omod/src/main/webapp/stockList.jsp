<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Stock selon les entrepot</title>
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

        /* Style du tableau */
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
    <script src="https://code.jentrepotSourceId.com/jentrepotSourceId-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function searchVenteDrug() {
            var dateDebut = $('#dateDebut').val();
            var dateFin = $('#dateFin').val();
            var medicament = $('#medicament').val();
            var numeroLot = $('#numeroLot').val();
            var entrepotSourceId = $('#entrepotSourceId').val();
            const contextPath = '${pageContext.request.contextPath}';

            // Gestion des dates
            if (!dateDebut) {
                let today = new Date().toISOString().split('T')[0];
                dateDebut = today;
                $('#dateDebut').val(today);
            }

            if (!dateFin) {
                let dateMinus3 = new Date();
                dateMinus3.setDate(dateMinus3.getDate() - 3);
                dateFin = dateMinus3.toISOString().split('T')[0];
                $('#dateFin').val(dateFin);
            }

            var start = new Date(dateDebut);
            var end = new Date(dateFin);

            if (dateDebut || dateFin) {
                if (start < end) {
                    alert("Erreur : La date de début doit être antérieure ou égale à la date de fin.");
                    return;
                }
            }

            // Requête AJAX pour chercher les ventes
            $.ajax({
                url: contextPath + '/module/mycashier/searchVenteDrug.form',
                type: 'GET',
                data: {
                    dateDebut: dateDebut,
                    dateFin: dateFin,
                    medicament: medicament,
                    numeroLot: numeroLot,
                    entrepotSourceId: entrepotSourceId
                },
             success: function (data) {
                               $('#resultsTable tbody').empty();
                               data.forEach(function (item) {
                                   var medicaments = [item.drug1, item.drug2, item.drug3].filter(Boolean).join(' ');

                                   var row = '<tr data-id="' + item.venteDrugId + '">' +
                                       '<td>' + item.dateVente + '</td>' +
                                       '<td>' + item.medicament + '</td>' +
                                       '<td>' + item.numeroLot + '</td>' +
                                       '<td>' + item.preparateur  + '</td>' +
                                       '<td>' + medicaments  + '</td>' +
                                       '<td>' + item.total  + '</td>' +
                                       '<td>' + item.reste + '</td>' +
                                        

                                        '<input type="hidden" class="venteDrugId" value="' + item.venteDrugId + '"/>' +
                                       '</tr>';
                                   $('#resultsTable tbody').append(row);
                               });


                // Ajout d'un événement de clic pour chaque ligne du tableau
                $('#resultsTable tbody tr').click(function () {
                    var venteDrugId = $(this).find('.venteDrugId').val();
                    window.location.href = contextPath + '/module/mycashier/venteProduit.form?venteDrugId=' + venteDrugId;
                });
                           },
            error: function (xhr, status, error) {
                console.error("Erreur lors de la requête : ", error);
            }

,
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

<script>
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
                        <td><fmt:formatDate value="${stock.datePeremption}" pattern="yyyy-MM-dd"/></td>
                    </tr>`;
                    stockTable.append(row);
                });
            })
            .catch(error => {
                console.error("Erreur lors de la recherche du stock:", error);
            });
    }
</script>

</body>
</html>