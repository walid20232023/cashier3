<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Recherche de Paiements</title>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .search-container {
            margin: 20px auto;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .search-title {
            color: #28a745;
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        tr:hover {
            background-color: #f1f1f1;
            cursor: pointer;
        }
        .btn-search {
            background-color: #28a745;
            color: white;
        }
        .table-container {
            margin-top: 30px;
        }
        #logOutput {
            margin-top: 20px;
            background-color: #e9ecef;
            padding: 10px;
            border-radius: 5px;
            font-family: monospace;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<div class="container search-container">
    <div class="search-title">Recherche de Paiements</div>
    <form id="searchForm" class="form-inline">
        <div class="row">
            <div class="col-md-3">
                <input type="date" class="form-control" id="startDate" name="startDate" placeholder="Date de début">
            </div>
            <div class="col-md-3">
                <input type="date" class="form-control" id="endDate" name="endDate" placeholder="Date de fin">
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" id="clientNom" name="clientNom" placeholder="Nom du client">
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" id="clientPrenom" name="clientPrenom" placeholder="Prénom du client">
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-3">
                <input type="text" class="form-control" id="operationType" name="operationType" placeholder="Type d'opération">
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" id="agentName" name="agentName" placeholder="Nom de l'agent">
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" id="assurance" name="assurance" placeholder="Assurance">
            </div>
            <div class="col-md-3">
                <button type="button" class="btn btn-search" onclick="searchPayments()">Rechercher</button>
            </div>
        </div>
    </form>

    <div class="table-container">
        <table class="table table-striped" id="resultsTable">
            <thead>
                <tr>
                    <th>Date de Paiement</th>
                    <th>Client</th>
                    <th>Type d'Opération</th>
                    <th>Agent</th>
                    <th>Montant</th>
                </tr>
            </thead>
            <tbody>
                <!-- Résultats injectés via JavaScript -->
            </tbody>
        </table>
    </div>

    <div id="logOutput">
        <p><strong>Logs :</strong> Aucune action n'a encore été effectuée.</p>
    </div>
</div>

<script>
    function formatDate(dateString) {
        return new Date(dateString).toLocaleDateString('fr-FR', { year: 'numeric', month: '2-digit', day: '2-digit' });
    }

    function searchPayments() {
        const startDate = $('#startDate').val();
        const endDate = $('#endDate').val();
        const clientNom = $('#clientNom').val();
        const clientPrenom = $('#clientPrenom').val();
        const operationType = $('#operationType').val();
        const agentName = $('#agentName').val();
        const assurance = $('#assurance').val();
        const contextPath = '${pageContext.request.contextPath}';

        $('#logOutput').html(`
            <p><strong>Logs :</strong></p>
            <p>Date de début: ${startDate || 'non précisé'}</p>
            <p>Date de fin: ${endDate || 'non précisé'}</p>
            <p>Nom du client: ${clientNom}</p>
            <p>Prénom du client: ${clientPrenom}</p>
            <p>Type d'opération: ${operationType}</p>
            <p>Nom de l'agent: ${agentName}</p>
            <p>Assurance: ${assurance}</p>
        `);

        $.ajax({
            url: contextPath + '/module/mycashier/searchPaymentDrug.form',
            type: 'GET',
            data: {
                startDate: startDate,
                endDate: endDate,
                clientNom: clientNom,
                clientPrenom: clientPrenom,
                operationType: operationType,
                agentName: agentName,
                assurance: assurance
            },
       success: function (data) {
           console.log(data); // Débogage pour voir le format des données

           const tbody = $('#resultsTable tbody');
           tbody.empty();

         data.forEach(function (payment) {
             // Formater chaque ligne de résultat en fonction du modèle fourni
             var row = '<tr data-id="' + payment.paymentDrugId + '">' +
                 '<td>' + payment.datePayment + '</td>' +
                 '<td>' + payment.clientNom + '</td>' +
                  '<td>' + payment.modePayment + '</td>' +
                 '<td>' + payment.agentName + '</td>' +

                 '<td>' + payment.montant + '</td>' +

             '</tr>';

             // Ajouter la ligne au tableau
             tbody.append(row);
         });

       }
,
            error: function (xhr, status, error) {
                console.error("Erreur lors de la requête : ", error);
                $('#logOutput').append(`<p style="color:red;">Erreur : ${error}</p>`);
            }
        });
    }

    $(document).ready(function () {
        searchPayments(); // Rechercher par défaut lors du chargement
    });
</script>

</body>
</html>
