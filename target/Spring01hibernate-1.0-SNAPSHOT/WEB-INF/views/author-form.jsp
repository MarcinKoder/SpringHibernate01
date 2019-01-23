<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie Autora</title>
</head>
<body>
<div>
    <form:form modelAttribute="author" method="post">
        <p>Imię : <form:input path="firstName"/><form:errors path="firstName"/></p>
        <p>Nazwisko: <form:input path="lastName"/><form:errors path="lastName"/></p>
        <p>PESEL: <form:input path="pesel"/><form:errors path="pesel"/></p>
        <p>Email: <form:input path="email"/><form:errors path="email"/></p>
        <p>Rok urodzenia: <form:input path="yearOfBirth"/><form:errors path="yearOfBirth"/></p>
        <p><input type="submit" value="Zapisz"/><input type="reset" value="Wyczyść"></p>
    </form:form>
</div>
</body>
</html>
