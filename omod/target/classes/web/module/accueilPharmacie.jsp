
    <title>Gestion de la pharmacie</title>
    <%@include file="layouts/_head.jsp"%>
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



<%@include file="layouts/_footer.jsp"%>

</body>
    </html>
