<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


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
            <img src="./static/spinner.gif">
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

                <a id="coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension1"
                   href="index_vente.html"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-credit-card"></i>
                    Vente des produits
                </a>
                <a id="coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension2"
                   href="index_caisse.html"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-credit-card"></i>
                    Caisse
                </a>
                <a id="coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension"
                   href="http://localhost:8080/openmrs/coreapps/findpatient/findPatient.page?app=coreapps.findPatient"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-search"></i>
                    Rechercher dossier de patient
                </a>
                <a id="org-openmrs-module-coreapps-activeVisitsHomepageLink-org-openmrs-module-coreapps-activeVisitsHomepageLink-extension"
                   href="http://localhost:8080/openmrs/coreapps/activeVisits.page?app=coreapps.activeVisits"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-calendar"></i>
                    Consultations actives
                </a>

                <a id="referenceapplication-vitals-referenceapplication-vitals-extension"
                   href="http://localhost:8080/openmrs/coreapps/findpatient/findPatient.page?app=referenceapplication.vitals"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-vitals"></i>
                    Enregistrer signes vitaux
                </a>
                <a id="referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension"
                   href="http://localhost:8080/openmrs/registrationapp/registerPatient.page?appId=referenceapplication.registrationapp.registerPatient"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-user"></i>
                    Enregistrer patient
                </a>

                <a id="appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension"
                   href="http://localhost:8080/openmrs//appointmentschedulingui/home.page"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-calendar"></i>
                    Programmation des rendez-vous
                </a>
                <a id="reportingui-reports-homepagelink-reportingui-reports-homepagelink-extension"
                   href="http://localhost:8080/openmrs/reportingui/reportsapp/home.page"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-list-alt"></i>

                    Rapports
                </a>

                <a id="coreapps-datamanagement-homepageLink-coreapps-datamanagement-homepageLink-extension"
                   href="http://localhost:8080/openmrs/coreapps/datamanagement/dataManagement.page"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-hdd"></i>

                    Gestion des données
                </a>

                <a id="org-openmrs-module-adminui-configuremetadata-homepageLink-org-openmrs-module-adminui-configuremetadata-homepageLink-extension"
                   href="http://localhost:8080/openmrs/adminui/metadata/configureMetadata.page"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-tasks"></i>
                    Configurer les métadonnées
                </a>

                <a id="coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension"
                   href="http://localhost:8080/openmrs/coreapps/systemadministration/systemAdministration.page"
                   class="btn btn-default btn-lg button app big align-self-center" type="button">
                    <i class="icon-cogs"></i>
                    Administration système
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


