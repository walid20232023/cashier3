<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>


<!DOCTYPE html>
<html>
<head>
    <title>Liste des Assurances</title>
    <style>
        /* Style for the table */
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
        tr:hover {background-color: #f5f5f5;}

        /* Style for the add button */
        .add-button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 0;
            font-size: 18px;
            color: white;
            background-color: #4CAF50;
            text-decoration: none;
            border-radius: 5px;
        }
        .add-button:hover {
            background-color: #45a049;
        }

        /* General style for the page */
        .container {
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Liste des Assurances</h1>
    <a href="<%= request.getContextPath() %>/module/mycashier/assuranceForm.form" class="add-button">
        <i class="icon-plus"></i> Ajouter Assurance
    </a>
    <table>
        <thead>
            <tr>
                <th>Nom Assurance</th>
                <th>Adresse</th>
                <th>Téléphone</th>
                <th>Date de Création</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="assurance" items="${assuranceList}">
                <tr onclick="window.location='<%= request.getContextPath() %>/module/mycashier/assuranceForm.form?id=${assurance.id}'">
                    <td>${assurance.name}</td>
                    <td>${assurance.address}</td>
                    <td>${assurance.telephone}</td>
                    <td>${assurance.localDateTime}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
</body>
</html>
