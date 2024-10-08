<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Recherche de Paiements</title>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
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
        /* Zone des logs */
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
    <form id="searchForm" action="<c:url value='/searchPaymentDrug.form'/>" method="GET">
        <div class="row">
            <div class="col-md-3">
                <label for="startDate">Date de début:</label>
                <input type="date" class="form-control" id="startDate" name="startDate">
            </div>
            <div class="col-md-3">
                <label for="endDate">Date de fin:</label>
                <input type="date" class="form-control" id="endDate" name="endDate">
            </div>
            <div class="col-md-3">
                <label for="clientNom">Nom du client:</label>
                <input type="text" class="form-control" id="clientNom" name="clientNom" placeholder="Nom du client">
            </div>
            <div class="col-md-3">
                <label for="clientPrenom">Prénom du client:</label>
                <input type="text" class="form-control" id="clientPrenom" name="clientPrenom" placeholder="Prénom du client">
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-3">
                <label for="operationType">Type d'opération:</label>
                <input type="text" class="form-control" id="operationType" name="operationType" placeholder="Type d'opération">
            </div>
            <div class="col-md-3">
                <label for="agentName">Nom de l'agent:</label>
                <input type="text" class="form-control" id="agentName" name="agentName" placeholder="Nom de l'agent">
            </div>
            <div class="col-md-3">
                <label for="assurance">Assurance:</label>
                <input type="text" class="form-control" id="assurance" name="assurance" placeholder="Assurance">
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-search mt-4">Rechercher</button>
            </div>
        </div>
    </form>

    <div class="table-container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Date de Paiement</th>
                    <th>Client</th>
                    <th>Type d'Opération</th>
                    <th>Agent</th>
                    <th>Montant</th>
                </tr>
            </thead>
            <tbody id="paymentResults">
                <!-- Les résultats de recherche seront injectés ici via JavaScript -->
            </tbody>
        </table>
    </div>

    <!-- Zone où afficher les logs -->
    <div id="logOutput">
        <p><strong>Logs :</strong> Aucune action n'a encore été effectuée.</p>
    </div>
</div>

<script>
    document.getElementById("searchForm").addEventListener("submit", function (e) {
        e.preventDefault();

        // Récupération des valeurs des champs du formulaire
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;
        const clientNom = document.getElementById("clientNom").value;
        const clientPrenom = document.getElementById("clientPrenom").value;
        const operationType = document.getElementById("operationType").value;
        const agentName = document.getElementById("agentName").value;
        const assurance = document.getElementById("assurance").value;

        // Logs dans la console
        console.log("Recherche de paiements avec les paramètres suivants:");
        console.log("Date de début: " + startDate);
        console.log("Date de fin: " + endDate);
        console.log("Nom du client: " + clientNom);
        console.log("Prénom du client: " + clientPrenom);
        console.log("Type d'opération: " + operationType);
        console.log("Nom de l'agent: " + agentName);
        console.log("Assurance: " + assurance);

        // Affichage des logs dans la zone dédiée sur la page
        document.getElementById("logOutput").innerHTML = `
            <p><strong>Logs :</strong></p>
            <p>Date de début: ${startDate}</p>
            <p>Date de fin: ${endDate}</p>
            <p>Nom du client: ${clientNom}</p>
            <p>Prénom du client: ${clientPrenom}</p>
            <p>Type d'opération: ${operationType}</p>
            <p>Nom de l'agent: ${agentName}</p>
            <p>Assurance: ${assurance}</p>
        `;

        // Requête pour chercher les paiements
        const contextPath = '${pageContext.request.contextPath}';
     fetch(`${contextPath}/searchPaymentDrug.form?startDate=${startDate}&endDate=${endDate}&clientNom=${clientNom}&clientPrenom=${clientPrenom}&operationType=${operationType}&agentName=${agentName}&assurance=${assurance}`)
    .then(response => response.json())
            .then(data => {
                const tbody = document.getElementById("paymentResults");
                tbody.innerHTML = "";

                data.forEach(payment => {
                    const tr = document.createElement("tr");
                    tr.innerHTML = `
                        <td>${payment.datePaiement}</td>
                        <td>${payment.clientNom} ${payment.clientPrenom}</td>
                        <td>${payment.assurance}</td>
                        <td>${payment.agentName}</td>
                        <td>${payment.montant}</td>
                    `;
                    tbody.appendChild(tr);
                });
            })
            .catch(error => {
                console.error("Erreur lors de la recherche des paiements:", error);
                document.getElementById("logOutput").innerHTML += `<p style="color:red;">Erreur : ${error}</p>`;
            });
    });

    // Exemple de log au chargement de la page
    console.log("Page de recherche des paiements chargée.");
</script>

</body>
</html>
