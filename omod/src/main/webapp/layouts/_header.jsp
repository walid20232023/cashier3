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