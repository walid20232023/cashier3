<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Ajouter/Modifier Service</title>
    <style>
        .container {
            padding: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
        }
        .form-group input, .form-group textarea, .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .submit-button {
            padding: 10px 20px;
            font-size: 18px;
            color: white;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .submit-button:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Ajouter/Modifier Service</h1>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>
<<<<<<< HEAD:mycashier/omod/src/main/webapp/serviceForm.jsp
    <form action="<%= request.getContextPath() %>/module/mycashier/serviceForm.form" method="post">
        <input type="hidden" id="id" name="id" value="${service.id}">
        <div class="form-group">
            <label for="name">Nom du service</label>
            <input type="text" id="name" name="name" value="${service.name}" required>
        </div>
        <div class="form-group">
            <label for="typeServiceId">Type du service</label>
            <select id="typeServiceId" name="typeServiceId" required>
                <c:forEach var="typeService" items="${typeServices}">
                    <option value="${typeService.id}" <c:if test="${typeService.id == service.typeService.id}">selected</c:if>>${typeService.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="price">Prix</label>
            <input type="number" id="price" name="price" value="${service.price}" required>
=======
    <form action="<%= request.getContextPath() %>/module/mycashier/assuranceForm.form" method="post">
        <input type="hidden" id="id" name="id" value="${myService.id}">
        <div class="form-group">
            <label for="name">Nom de l'Assurance</label>
            <input type="text" id="name" name="name" value="${myService.name}" required>
        </div>
        <div class="form-group">
            <label for="address">Adresse</label>
            <input type="text" id="address" name="address" value="${myService.address}">
        </div>
        <div class="form-group">
            <label for="telephone">Téléphone</label>
            <input type="text" id="telephone" name="telephone" value="${myService.telephone}">
>>>>>>> b66d7c2418668d80ee363f9d9ce4b9656fc2ba10:mycashier/omod/src/main/webapp/myServiceForm.jsp
        </div>
        <button type="submit" class="submit-button">Enregistrer Service</button>
    </form>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
</body>
</html>
