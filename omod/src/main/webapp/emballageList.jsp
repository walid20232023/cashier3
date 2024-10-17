<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Emballages</title>
    <style>
        /* Style pour le tableau */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 18px;
            text-align: left;
        }
        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }

        /* Style pour le bouton d'ajout */
        .add-button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 0;
            font-size: 18px;
            color: white;
            background-color: #4CAF50;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .add-button:hover {
            background-color: #45a049;
        }

        /* Style général pour la page */
        .container {
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Liste des Types de Services</h1>
    <a href="<%= request.getContextPath() %>/module/mycashier/emballageForm.form" class="add-button">
        <i class="icon-plus"></i> Ajouter un Emballage
    </a>
    <table>
        <thead>
            <tr>
                <th>Libellé</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="emballage" items="${emballages}">
                <tr onclick="window.location='<%= request.getContextPath() %>/module/mycashier/emballageForm.form?id=${emballage.id}'">
                    <td>${emballage.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>
</body>
</html>
