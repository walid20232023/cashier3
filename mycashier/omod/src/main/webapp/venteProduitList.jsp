<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Recherche des Ventes de Médicaments</title>
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
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
                                       '<td>' + medicaments + '</td>' +
                                       '</tr>';
                                   $('#resultsTable tbody').append(row);
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
    </script>
</head>
<body>
    <div class="container">
        <h1>Recherche des Ventes de Médicaments</h1>
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

        <table class="table table-striped" id="resultsTable">
            <thead>
                <tr>
                    <th>Date de Vente</th>
                    <th>Nom du Client</th>
                    <th>Prénom du Client</th>
                    <th>Médicaments</th>
                </tr>
            </thead>
            <tbody>
                <!-- Les résultats de la recherche seront ajoutés ici via JavaScript -->
            </tbody>
        </table>
    </div>
</body>
</html>
