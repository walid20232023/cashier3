<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <%@include file="layouts/_head.jsp"%>
        <title>Ventes de produits</title>
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
                        <ul id="breadcrumbs">
                            <li>
                                <a href="${pageContext.request.contextPath}/"><i class="icon-home small"></i></a>
                            </li>
                            <li>
                                <i class="icon-chevron-right link"></i>
                                <a href="${pageContext.request.contextPath}/module/mycashier/accueilPharmacie.form">
                                    Pharamacie
                                </a>
                            </li>
                            <li>
                                <i class="icon-chevron-right link"></i>
                                Historique de Ventes de produits
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="card">
                        <div class="card-header text-center" >
                            <h1>Liste de ventes de produits</h1>
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
                    </div>
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
        <script>
            function searchVenteDrug() {
                var startDate = $('#startDate').val();
                var endDate = $('#endDate').val();
                var clientNom = $('#clientNom').val();
                var clientPrenom = $('#clientPrenom').val();
                var query = $('#query').val();
                const contextPath = '${pageContext.request.contextPath}';

                // Gestion des dates
                if (!startDate) {
                    let today = new Date().toISOString().split('T')[0];
                    startDate = today;
                    $('#startDate').val(today);
                }

                if (!endDate) {
                    let dateMinus3 = new Date();
                    dateMinus3.setDate(dateMinus3.getDate() - 3);
                    endDate = dateMinus3.toISOString().split('T')[0];
                    $('#endDate').val(endDate);
                }

                var start = new Date(startDate);
                var end = new Date(endDate);

                if (startDate || endDate) {
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
                        startDate: startDate,
                        endDate: endDate,
                        clientNom: clientNom,
                        clientPrenom: clientPrenom,
                        query: query
                    },
                    success: function (data) {
                        $('#resultsTable tbody').empty();
                        data.forEach(function (item) {
                            var medicaments = [item.drug1, item.drug2, item.drug3].filter(Boolean).join(' ');

                            var row = '<tr data-id="' + item.venteDrugId + '">' +
                                '<td>' + item.dateVente + '</td>' +
                                '<td>' + item.clientNom + '</td>' +
                                '<td>' + item.clientPrenom + '</td>' +
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
    </body>
</html>
