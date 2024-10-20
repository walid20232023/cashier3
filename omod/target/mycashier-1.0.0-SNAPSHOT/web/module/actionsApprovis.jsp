<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="layouts/_head.jsp"%>
    <title>Gestion d'approvisionnement</title>
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
                        Approvisionnement
                    </li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-12 col-sm-12 col-md-12 col-lg-12 homeList" id="apps">

                <a href="${pageContext.request.contextPath}/module/mycashier/approvisForm.form'"
                   class="btn-secondary btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-currency-dollar"></i>
                    Créer un Approvisionnement
                </a>
                <a href="${pageContext.request.contextPath}/module/mycashier/approvisionnementList.form"
                   class="btn-secondary btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-pencil"></i>
                    Historique des Approvisionnements
                </a>
                <a href="${pageContext.request.contextPath}/module/mycashier/searchPaymentDrugForm.form"
                   class="btn-secondary btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-archive"></i>
                    Approvisionnements en Cours de Préparation
                </a>
                <a href="${pageContext.request.contextPath}/module/mycashier/actionsApprovis.form"
                   class="btn-secondary btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-bag-check"></i>
                    Approvisionnements en Attente de Validation
                </a>

            </div>
        </div>

    </div>
</div>



<%@include file="layouts/_footer.jsp"%>

</body>
</html>
