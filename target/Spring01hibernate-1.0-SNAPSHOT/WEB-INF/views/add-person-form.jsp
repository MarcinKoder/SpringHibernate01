<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie osoby</title>
</head>
<body>
<div class="container">
    <div class="row center">
        <form:form method="post" modelAttribute="person">
            Login: <form:input path="login"/><br/>
            Email: <form:input path="email" type="email"/><br/>
            Password: <form:password path="password"/><br/>
            <input type="submit" value="Zapisz"/>
            <input type="reset" value="Wyczyść"/>
        </form:form>
    </div>
</div>
</body>
</html>
