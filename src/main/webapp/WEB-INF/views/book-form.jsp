<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie książki</title>
</head>
<body>
<div>
    <form:form modelAttribute="book" method="post">
        <p>Tytuł : <form:input path="title"/><form:errors path="title"/> </p>
        <p>Opis: <form:textarea path="description"/><form:errors path="description"/></p>
        <p>Ocena: <form:input path="rating"/><form:errors path="rating"/></p>
        <p>Strony: <form:input path="pages" type="number"/><form:errors path="pages"/></p>
        <p>Wydawca: <form:select path="publisher"
                                 items="${publishers}"
                                 itemLabel="name"
                                 itemValue="id"/><form:errors path="publisher"/></p>
        <p>Autor: <form:select path="authors" items="${authors}" itemValue="id" multiple="true"/><form:errors path="authors"/></p>
        <p><input type="submit" value="Zapisz"/><input type="reset" value="Wyczyść"></p>
    </form:form>
</div>
</body>
</html>
