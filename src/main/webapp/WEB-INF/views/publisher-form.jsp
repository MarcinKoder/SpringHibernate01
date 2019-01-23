<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie wydawców</title>
</head>
<body>
<div>
    <form:form modelAttribute="publisher" method="post">
        <p>Nazwa : <form:input path="name"/><form:errors path="name"/></p>
        <p>NIP : <form:input path="nip"/><form:errors path="nip"/></p>
        <p>REGON : <form:input path="regon"/><form:errors path="regon"/></p>
        <p><input type="submit" value="Zapisz"/><input type="reset" value="Wyczyść"></p>
    </form:form>
</div>
</body>
</html>
