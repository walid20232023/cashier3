<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des approvisionnments</title>
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
    <div class="container">
        <h2>Liste des Approvisionnements</h2>

        <!-- Champs de recherche -->
        <div class="row mb-4">
            <div class="col-md-3">
                <label>Date Début:</label>
                <input type="date" id="dateDebut" class="form-control">
            </div>
            <div class="col-md-3">
                <label>Date Fin:</label>
                <input type="date" id="dateFin" class="form-control">
            </div>
            <div class="col-md-3">
                <label>Médicament:</label>
                <input type="text" id="medicament" class="form-control" placeholder="Nom du médicament">
            </div>
            <div class="col-md-3">
                <label>Numéro de Lot:</label>
                <input type="text" id="numeroLot" class="form-control">
            </div>
            <div class="col-md-3 mt-2">
                <label>Entrepôt Source:</label>
                <select id="entrepotSourceId" class="form-control">
                    <option value="">Sélectionnez</option>
                    <c:forEach var="entrepot" items="${entrepots}">
                        <option value="${entrepot.id}">${entrepot.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3 mt-2">
                <label>Entrepôt Cible:</label>
                <select id="entrepotCibleId" class="form-control">
                    <option value="">Sélectionnez</option>
                    <c:forEach var="entrepot" items="${entrepots}">
                        <option value="${entrepot.id}">${entrepot.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3 mt-2">
                <label>Périmé Avant:</label>
                <input type="date" id="perimeAvant" class="form-control">
            </div>
            <div class="col-md-3 mt-2">
                <button class="btn btn-primary mt-4" id="searchButton">Rechercher</button>
            </div>
        </div>

        <!-- Tableau des résultats -->
        <table class="table table-bordered" id="approvisionnementTable">
            <thead>
                <tr>
                    <th>Date Approvisionnement</th>
                    <th>Médicament</th>
                    <th>Numéro de Lot</th>
                    <th>Quantité</th>
                    <th>Entrepôt Source</th>
                    <th>Entrepôt Cible</th>
                    <th>Date de Péremption</th>
                </tr>
            </thead>
            <tbody id="tableBody">
                <!-- Les données seront insérées ici via AJAX -->
            </tbody>
        </table>
    </div>

    <script>
        $(document).ready(function() {
            $('#searchButton').click(function() {
                // Collecter les valeurs des champs de recherche
                const dateDebut = $('#dateDebut').val();
                const dateFin = $('#dateFin').val();
                const medicament = $('#medicament').val();
                const numeroLot = $('#numeroLot').val();
                const entrepotSourceId = $('#entrepotSourceId').val();
                const entrepotCibleId = $('#entrepotCibleId').val();
                const perimeAvant = $('#perimeAvant').val();

                // Envoyer la requête AJAX au serveur
                $.ajax({
                    url: '/module/mycashier/searchApprovisionnement.form',
                    method: 'GET',
                    data: {
                        dateDebut,
                        dateFin,
                        medicament,
                        numeroLot,
                        entrepotSourceId,
                        entrepotCibleId,
                        perimeAvant
                    },
                    success: function(data) {
                        $('#tableBody').empty(); // Vider les résultats précédents
                        data.forEach(item => {
                            $('#tableBody').append(`
                                <tr>
                                    <td>${item.dateApprovisionnement}</td>
                                    <td>${item.medicament}</td>
                                    <td>${item.numeroLot}</td>
                                    <td>${item.quantite}</td>
                                    <td>${item.entrepotSource.name}</td>
                                    <td>${item.entrepotCible.name}</td>
                                    <td>${item.datePeremption}</td>
                                </tr>
                            `);
                        });
                    }
                });
            });
        });
    </script>
</body>
</html>