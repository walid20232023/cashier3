<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Actions Approvisionnement</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/openmrs.css'/>"> <!-- Assurez-vous que le chemin est correct -->
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Actions Approvisionnement</h2>
        <div class="row justify-content-center mt-4">
            <div class="col-md-4">
                <a href="<c:url value='/module/mycashier/approvisForm.form'/>" class="btn btn-primary btn-lg btn-block">Créer un Approvisionnement</a>
            </div>
            <div class="col-md-4">
                <a href="<c:url value='/module/mycashier/approvisionnementList.form'/>" class="btn btn-secondary btn-lg btn-block">Historique des Approvisionnements</a>
            </div>
            <div class="col-md-4">
                <button class="btn btn-info btn-lg btn-block" disabled>Approvisionnements en Cours de Préparation</button>
            </div>
            <div class="col-md-4">
                <button class="btn btn-warning btn-lg btn-block" disabled>Approvisionnements en Attente de Validation</button>
            </div>
        </div>
    </div>
    <script src="<c:url value='/resources/js/bootstrap.bundle.min.js'/>"></script> <!-- Assurez-vous que le chemin est correct -->
</body>
</html>
