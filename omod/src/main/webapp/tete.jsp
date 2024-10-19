<!-- Bootstrap Icons CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.3/font/bootstrap-icons.min.css" rel="stylesheet">

<link rel="shortcut icon" type="image/ico" href="http://localhost:8080/openmrs/images/openmrs-favicon.ico">
<link rel="icon" type="image/png\" href="http://localhost:8080/openmrs/images/openmrs-favicon.png">
<!-- Latest compiled and minified CSS -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery-1.12.4.min.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery-ui-1.9.2.custom.min.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/underscore-min.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/knockout-2.2.1.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/emr.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery.toastmessage.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery.simplemodal.1.4.4.min.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/popper.min.js">
<openmrs:htmlInclude file="/moduleResources/mycashier/js/bootstrap.min.js">
    
<openmrs:htmlInclude file="/moduleResources/mycashier/css/jquery-ui-1.9.2.custom.min.css" type="text/css">
<openmrs:htmlInclude file="/moduleResources/mycashier/css/jquery.toastmessage.css" type="text/css">
<openmrs:htmlInclude file="/moduleResources/mycashier/css/home.css" type="text/css">
<openmrs:htmlInclude file="/moduleResources/mycashier/css/bootstrap.min.css" type="text/css">
<openmrs:htmlInclude file="/moduleResources/mycashier/css/header.css" type="text/css">
<openmrs:htmlInclude file="/moduleResources/mycashier/css/referenceapplication.css" type="text/css">
<openmrs:htmlInclude file="/moduleResources/mycashier/css/styles.css">

<openmrs:htmlInclude file="/moduleResources/mycashier/css/csrfguard">

<style>
    /* Styles personnalisés */
    body {
        background-color: #f4f4f9;
    }
    .navbar {
        background-color: #29AB87;
    }
    .navbar-dark .navbar-nav .nav-link {
        color: #ffffff;
    }
    .navbar-dark .navbar-nav .nav-link:hover {
        color: #e8f5e9;
    }
    .card {
        margin: 20px 0;
    }
    .card-header {
        background-color: #29AB87;
        color: #ffffff;
    }
    .btn-custom {
        background-color: #29AB87;
        color: #ffffff;
    }
    .btn-custom:hover {
        background-color: #248739;
    }
    .icon-view, .icon-delete, .icon-edit {
        cursor: pointer;
        color: #29AB87;
    }
    .icon-view:hover, .icon-delete:hover, .icon-edit:hover {
        color: #248739;
    }
    .form-table th, .form-table td {
        text-align: center;
        vertical-align: middle;
    }
    .form-table th {
        background-color: #e8f5e9;
    }
    .medicament-col {
        width: 30%; /* Augmenter la largeur pour le champ Médicament */
    }
    .quantity-col, .pu-col, .total-col, .stock-ph-col, .stock-mg-col {
        width: 10%;
    }
    .form-table td, .form-table th {
        white-space: nowrap;
    }
    /* Style pour les listes déroulantes */
    #clientSearchResults, #drugSearchResults {
        position: absolute;
        width: 100%;
        max-height: 200px;
        overflow-y: auto;
        z-index: 1000;
        background-color: white;
        border: 1px solid #ccc;
    }
    .list-group-item {
        cursor: pointer;
    }
    .list-group-item:hover {
        background-color: #f0f0f0;
    }
    /* Style spécifique pour les champs d'assurance */
    .form-group.assurance input {
        background-color: #e0f7fa; /* Bleu clair */
        border: 1px solid #b2ebf2; /* Bordure légèrement plus foncée */
        border-radius: 4px; /* Coins légèrement arrondis */
    }
    .form-group.assurance {
        border: 1px solid #b2ebf2; /* Bordure autour du champ */
        border-radius: 4px; /* Coins légèrement arrondis */
        padding: 10px; /* Espacement intérieur */
        margin-bottom: 10px; /* Espacement entre les champs */
    }

    #partAssurance, #partClient, #total {
        font-weight: bold;
        color: #2c3e50;
        background-color: #ecf0f1;
        text-align: right;
    }

    button.btn-success {
        background-color: #27ae60;
        border-color: #27ae60;
    }

    #partAssurance, #partClient, #total {
        font-weight: bold;
        color: #2c3e50;
        background-color: #ecf0f1;
        text-align: right;
        border-radius: 6px; /* Ajout de bordures arrondies */
        padding: 8px; /* Ajout d'un peu d'espace intérieur */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Ombre légère pour l'effet de relief */
    }

    button.btn-success {
        background-color: #27ae60;
        border-color: #27ae60;
        border-radius: 8px; /* Bordures arrondies */
        padding: 10px 20px; /* Augmentation de la taille du bouton */
        transition: background-color 0.3s ease, transform 0.3s ease; /* Transition douce */
    }

    button.btn-success:hover {
        background-color: #229954; /* Changement de couleur au survol */
        transform: scale(1.05); /* Léger zoom au survol */
    }

    /* Styles pour les champs de texte */
    .custom-input {
        font-weight: bold;
        color: #34495e; /* Couleur bleu-gris */
        background-color: #eaf2f8; /* Bleu très clair */
        border: 2px solid #5dade2; /* Bordure bleu moyen */
        border-radius: 8px; /* Bordures arrondies */
        text-align: right;
        padding: 10px; /* Espace intérieur pour plus de confort */
        box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1); /* Ombre intérieure pour un effet enfoncé */
    }

    /* Style pour le bouton */
    .custom-button {
        font-size: 1rem; /* Taille du texte réduite */
        padding: 8px 16px; /* Taille ajustée pour un bouton plus petit */
        background-color: #3498db; /* Bleu vif */
        border-color: #2980b9; /* Bleu foncé */
        border-radius: 8px; /* Bordures arrondies */
        transition: background-color 0.3s ease, transform 0.3s ease; /* Transition douce */
        align-self: center; /* Alignement centré verticalement */
    }

    .custom-button:hover {
        background-color: #2980b9; /* Couleur de survol */
        transform: scale(1.05); /* Léger zoom au survol */
    }

    /* Bouton Valider désactivé (gris clair) */
    .btn-disabled {
        background-color: lightgray;
        color: #fff;
        cursor: not-allowed;
        border-color: lightgray;
    }

    /* Bouton Valider activé (rouge vif) */
    .btn-enabled {
        background-color: red;
        color: #fff;
        border-color: red;
    }





</style>