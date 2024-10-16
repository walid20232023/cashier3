<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Formulaire d'Approvisionnement</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>"> <!-- Votre fichier CSS personnalisé -->
</head>
<body>
    <div class="container">
        <h2>Formulaire d'approvisionnement</h2>

        <!-- Section 1: Détails de l'approvisionnement -->
        <div class="form-group">
            <label>Entrepôt Source:</label>
            <input type="text" class="form-control" value="${entrepotSource}" readonly />
        </div>

        <div class="form-group">
            <label>Choisir Entrepôt Cible:</label>
            <select id="entrepotCible" class="form-control" required>
                <option value="">Sélectionnez un entrepôt</option>
                <!-- Remplir les entrepôts disponibles -->
                <c:forEach var="entrepot" items="${entrepots}">
                    <option value="${entrepot.id}">${entrepot.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Section 2: Recherche de médicaments -->
        <div class="form-group">
            <label>Rechercher Médicament:</label>
            <input type="text" id="medicamentSearch" class="form-control" placeholder="Nom du médicament" />
            <div id="medicamentResults" class="results-list"></div>
        </div>

        <!-- Section 3: Détails du médicament sélectionné -->
        <div class="form-group">
            <label>Nom du Médicament:</label>
            <input type="text" id="nomMedicament" class="form-control" readonly />
        </div>

        <div class="form-group">
            <label>Quantité en Stock:</label>
            <input type="text" id="quantityStock" class="form-control" readonly />
        </div>

        <div class="form-group">
            <label>Quantité à Approvisionner:</label>
            <input type="number" id="quantityApprovis" class="form-control" required />
        </div>

        <div class="form-group">
            <label>Numéro de Lot:</label>
            <input type="text" id="numeroLot" class="form-control" required />
        </div>

        <div class="form-group">
            <label>Date de Péremption:</label>
            <input type="date" id="datePeremption" class="form-control" required />
        </div>

        <button type="submit" class="btn btn-primary" id="submitApprovis">Soumettre</button>
    </div>

    <script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        $(document).ready(function() {
            // AJAX pour la recherche de médicaments
            $('#medicamentSearch').on('input', function() {
                const query = $(this).val();
                $.ajax({
                    url: '/module/mycashier/searchApprovisionnement.form',
                    method: 'GET',
                    data: { medicament: query },
                    success: function(data) {
                        $('#medicamentResults').empty(); // Clear previous results
                        data.forEach(item => {
                            $('#medicamentResults').append(
                                `<div class="result-item" data-id="${item.id}" data-name="${item.name}" data-stock="${item.quantityStock}">
                                    ${item.name} (Stock: ${item.quantityStock})
                                </div>`
                            );
                        });
                    }
                });
            });

            // Remplir les champs lorsque le médicament est sélectionné
            $(document).on('click', '.result-item', function() {
                $('#nomMedicament').val($(this).data('name'));
                $('#quantityStock').val($(this).data('stock'));
                $('#medicamentResults').empty(); // Clear results
            });
        });
    </script>
</body>
</html>

