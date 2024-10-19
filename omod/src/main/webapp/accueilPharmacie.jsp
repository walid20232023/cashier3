<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/template/include.jsp"%>


<!DOCTYPE html>
<html>
<head>
    <title>Gestion de la pharmacie</title>
    <%@include file="tete.jsp"%>
</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark navigation">
        <div class="logo">
            <a href="http://localhost:8080/openmrs/">
                <img src="${pageContext.request.contextPath}/moduleResources/mycashier/css/openmrs-with-title-small.png">
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto user-options">
                <li class="nav-item identifier" style="cursor: pointer;">


                    <i class="icon-user small"></i>
                    admin

                    <i class="icon-caret-down appui-icon-caret-down link"></i><i class="icon-caret-up link appui-toggle"
                                                                                 style="display: none;"></i>
                    <ul id="user-account-menu" class="appui-toggle">

                        <li>
                            <a id="" href="http://localhost:8080/openmrs/adminui/myaccount/myAccount.page">
                                Mon compte
                            </a>
                        </li>

                    </ul>

                </li>
                <li class="change-location">
                    <a href="javascript:void(0);">
                        <i class="icon-map-marker small"></i>
                        <span id="selected-location" data-bind="text: text"
                              location-uuid="b1a8b05e-3542-4037-bbd3-998ee9c40574">Inpatient Ward</span>
                        <i class="icon-caret-down link" style=""></i>
                    </a>
                </li>
                <li class="nav-item logout">
                    <a href="http://localhost:8080/openmrs/appui/header/logout.action?successUrl=openmrs">
                        Déconnexion
                        <i class="icon-signout small"></i>
                    </a>
                </li>
            </ul>


        </div>
    </nav>
    <div id="session-location">
        <div id="spinner" style="position:absolute; display:none">
            <img src="${pageContext.request.contextPath}/moduleResources/mycashier/css/spinner.gif">
        </div>
        <ul class="select">
            <li class="selected" locationuuid="b1a8b05e-3542-4037-bbd3-998ee9c40574" locationid="6"
                locationname="Inpatient Ward">Inpatient Ward
            </li>
            <li locationuuid="2131aff8-2e2a-480a-b7ab-4ac53250262b" locationid="4" locationname="Isolation Ward">
                Isolation Ward
            </li>
            <li locationuuid="7fdfa2cb-bc95-405a-88c6-32b7673c0453" locationid="3" locationname="Laboratory">
                Laboratory
            </li>
            <li locationuuid="58c57d25-8d39-41ab-8422-108a0c277d98" locationid="7" locationname="Outpatient Clinic">
                Outpatient Clinic
            </li>
            <li locationuuid="7f65d926-57d6-4402-ae10-a5b3bcbf7986" locationid="2" locationname="Pharmacy">Pharmacy</li>
            <li locationuuid="6351fcf4-e311-4a19-90f9-35667d99a8af" locationid="5" locationname="Registration Desk">
                Registration Desk
            </li>
        </ul>

    </div>
</header>

<ul id="breadcrumbs"></ul>

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
            <div class="col-12 col-sm-12 col-md-12 col-lg-12" id="title"  id="title">
                <h2> PHARMATIE </h2>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-sm-12 col-md-12 col-lg-12 homeList" id="apps">

                <a href="${pageContext.request.contextPath}/module/mycashier/venteProduit.form"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-currency-dollar"></i>
                    Créer une vente
                </a>
                <a href="${pageContext.request.contextPath}/module/mycashier/venteProduitList.form"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-pencil"></i>
                    Éditer une vente
                </a>
                <a href="${pageContext.request.contextPath}/module/mycashier/validatedDrugsList.form"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-bag-plus"></i>
                    Encaisser une vente
                </a>
                <a href="${pageContext.request.contextPath}/module/mycashier/validatedDrugsList.form"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-cash"></i>
                    Encaisser une vente
                </a>

                <a href="${pageContext.request.contextPath}/module/mycashier/searchPaymentDrugForm.form"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-archive"></i>
                    Historique des ventes
                </a>
                <a href="${pageContext.request.contextPath}/module/mycashier/actionsApprovis.form"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-bag-check"></i>
                    Approvisionnement
                </a>

                <a href="${pageContext.request.contextPath}/module/mycashier/displayStock.form"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="bi bi-clock-history"></i>
                    Stock des médicaments
                </a>
            </div>
        </div>

    </div>
</div>

<script id="breadcrumb-template" type="text/template">
    <li>
        {{ if (!first) { }}
        <i class="icon-chevron-right link"></i>
        {{ } }}
        {{ if (!last && breadcrumb.link) { }}
        <a href="{{= breadcrumb.link }}">
            {{ } }}
            {{ if (breadcrumb.icon) { }}
            <i class="{{= breadcrumb.icon }} small"></i>
            {{ } }}
            {{ if (breadcrumb.label) { }}
            {{= breadcrumb.label }}
            {{ } }}
            {{ if (!last && breadcrumb.link) { }}
        </a>
        {{ } }}
    </li>
</script>
<script type="text/javascript">
    jq(function () {
        emr.updateBreadcrumbs();
    });

    // global error handler
    jq(document).ajaxError(function (event, jqxhr) {
        emr.redirectOnAuthenticationFailure(jqxhr);
    });

    var featureToggles = {};


</script>
</body>
</html>


