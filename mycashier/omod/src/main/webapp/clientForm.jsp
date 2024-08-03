<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Client Form</title>
</head>
<body>
    <h1>Client Form</h1>
    <form:form modelAttribute="client" method="post" action="${pageContext.request.contextPath}/module/mycashier/client.form">
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>First Names:</td>
                <td><form:input path="firstnames" /></td>
            </tr>
            <tr>
                <td>Birth Date:</td>
                <td>
                    <!-- Use HTML input with type date -->
                    <input type="date" name="birthDate" value="${client.birthDate != null ? client.birthDate.getTime() : ''}" />
                </td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><form:input path="age" /></td>
            </tr>
            <tr>
                <td>Sex:</td>
                <td>
                    <form:select path="sex">
                        <form:option value="M" label="Male" />
                        <form:option value="F" label="Female" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address" /></td>
            </tr>
            <tr>
                <td>Telephone:</td>
                <td><form:input path="telephone" /></td>
            </tr>
            <tr>
                <td>Assurances:</td>
                <td>
                    <form:select path="assuranceList" multiple="true">
                        <c:forEach items="${assurances}" var="assurance">
                            <form:option value="${assurance.id}" label="${assurance.name}" />
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
