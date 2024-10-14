<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire de paiement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ms/uiframework/resource/uicommons/styles/openmrs.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f0f8f8;
        }

        h1 {
            color: #00796b;
            text-align: center;
            margin-top: 20px;
        }

        .section {
            margin-top: 30px;
            padding: 15px;
            border-radius: 10px;
            background-color: #e0f7fa;
            border: 1px solid #006d5b;
        }

        .section-header {
            background-color: #004d40;
            color: white;
            padding: 10px;
            border-radius: 5px;
        }

        .btn-primary {
            background-color: #00796b;
            border-color: #004d40;
        }

        .btn-primary:hover {
            background-color: #004d40;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // Variable pour stocker la valeur initiale de vente.reste
    var resteInitial = 0;

    // Calcul de la part client au chargement de la page
    function calculatePartClient() {
        var total = parseFloat($('#totalVente').val());
        var partAssurance = parseFloat($('#partAssurance').val());
        var partClient = total - partAssurance;
        $('#partClient').val(partClient.toFixed(2));
    }

    // Calcul automatique des champs reliquat à rendre, reliquat rendu et somme payée
    function calculatePaymentDetails() {
        var partClient = parseFloat($('#partClient').val());
        var sommeRendue = parseFloat($('#sommeRendue').val());
        var relicatRendu = parseFloat($('#relicatRendu').val());

        if (!isNaN(sommeRendue)) {
            // Calcul du reliquat à rendre
            var relicatARendre = sommeRendue - partClient;
            $('#relicatARendre').val(relicatARendre.toFixed(2));

            // Calcul de la somme payée si le reliquat rendu est modifié
            var sommePayee = sommeRendue - relicatRendu;
            $('#sommePayee').val(sommePayee.toFixed(2));

            // Calcul du reste à payer
            calculateResteAPayer(sommePayee);
        }
    }

    // Calculer le reste à payer en utilisant la valeur initiale de vente.reste
    function calculateResteAPayer(sommePayee) {
        var nouveauReste = resteInitial - sommePayee;
        $('#resteAPayer').val(nouveauReste.toFixed(2));
    }

    $(document).ready(function () {
        // Initialiser la part client dès le chargement de la page
        calculatePartClient();

        // Initialiser les valeurs par défaut
        setDefaultValues();

        // Capture de l'appui sur "Entrée" pour recalculer les champs
        $('#sommeRendue, #relicatRendu').on('keypress', function (e) {
            if (e.which === 13) {  // Touche "Entrée"
                e.preventDefault();
                calculatePaymentDetails();
            }
        });

        // Confirmation avant soumission
        $('#submitForm').click(function (e) {
            e.preventDefault();
            var confirmed = confirm("Êtes-vous sûr de vouloir valider ce paiement ?");
            if (confirmed) {
                $('#paymentForm').submit();
            }
        });

        // Définir des valeurs par défaut pour les champs
        function setDefaultValues() {
            $('#sommeRendue').val($('#sommeRendue').val() || 0);
            $('#relicatRendu').val($('#relicatRendu').val() || 0);
            $('#sommePayee').val($('#sommePayee').val() || 0);
            resteInitial = parseFloat('${vente.reste}');  // Stocker la valeur initiale de vente.reste
            $('#resteAPayer').val(resteInitial.toFixed(2));  // Afficher la valeur initiale
        }
    });
</script>

</head>
<body>
    <div class="container">
        <h1>Formulaire de Paiement</h1>

        <!-- Section identité du client -->
        <div class="section">
            <div class="section-header">Identité du client</div>
            <div class="row mt-3">
                <div class="col-md-3">
                    <label for="clientNom">Nom:</label>
                    <input type="text" class="form-control" id="clientNom" value="${clientNom}" readonly>
                </div>
                <div class="col-md-3">
                    <label for="clientPrenom">Prénom:</label>
                    <input type="text" class="form-control" id="clientPrenom" value="${clientPrenom}" readonly>
                </div>
                <div class="col-md-3">
                    <label for="clientSexe">Sexe:</label>
                    <input type="text" class="form-control" id="clientSexe" value="${clientSexe}" readonly>
                </div>
                <div class="col-md-3">
                    <label for="clientDob">Date de naissance:</label>
                    <input type="date" class="form-control" id="clientDob" value="${clientDob}" readonly>
                </div>
            </div>
        </div>

        <!-- Section de la vente -->
        <div class="section">
            <div class="section-header">Détails de la Vente</div>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="totalVente">Prix total:</label>
                    <input type="text" class="form-control" id="totalVente" value="${vente.total}" readonly>
                </div>
                <div class="col-md-4">
                    <label for="partAssurance">Part Assurance: ${vente.assurance != null ? vente.assurance : ''}</label>
                    <input type="text" class="form-control" id="partAssurance" value="${vente.partAssurance}" readonly>
                </div>

                <div class="col-md-4">
                    <label for="partClient">Part Client à payer:</label>
                    <input type="text" class="form-control" id="partClient" readonly>
                </div>
            </div>


        </div>

        <!-- Section paiement -->
        <div class="section">
            <div class="section-header">Paiement</div>
     <form id="paymentForm" action="${pageContext.request.contextPath}/module/mycashier/processPayment.form" method="POST">
         <input type="hidden" name="venteDrugId" value="${vente.id}">

         <div class="row mt-3">
             <div class="col-md-4">
                 <label for="sommeRendue">Somme remise:</label>
                 <input type="number" class="form-control" id="sommeRendue" name="sommeRendue" placeholder="Somme rendue" required>
             </div>
             <div class="col-md-4">
                 <label for="relicatARendre">Reliquat à rendre:</label>
                 <input type="number" class="form-control" id="relicatARendre" name="relicatARendre" readonly>
             </div>
             <div class="col-md-4">
                 <label for="relicatRendu">Reliquat rendu:</label>
                 <input type="number" class="form-control" id="relicatRendu" name="relicatRendu" placeholder="Relicat rendu" required>
             </div>
         </div>

         <div class="row mt-3">
             <div class="col-md-4">
                 <label for="sommePayee">Somme payée:</label>
                 <input type="number" class="form-control" id="sommePayee" name="sommePayee" readonly>
             </div>
             <div class="col-md-4">
                 <label for="resteAPayer">Reste à payer:</label>
                 <input type="number" class="form-control" id="resteAPayer" name="resteAPayer" value="${vente.reste}" readonly>
             </div>
             <div class="col-md-4">
                 <label for="modePaiement">Mode de paiement:</label>
                 <select class="form-control" id="modePaiement" name="modePaiement" required>
                     <option value="especes" selected>En espèces</option>
                     <option value="provision">Par provision</option>
                 </select>
             </div>
         </div>

         <div class="row mt-4">
             <div class="col-md-12 text-center">
                 <button type="button" id="submitForm" class="btn btn-primary">Valider</button>
             </div>
         </div>
     </form>



        </div>
    </div>
</body>
</html>
