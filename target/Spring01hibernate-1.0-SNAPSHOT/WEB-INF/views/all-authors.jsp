<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
    <title>All authors</title>
</head>
<body>
<div>
    <table>
        <thead>
        <tr>
            <th>Lp.</th>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>PESEL</th>
            <th>Email</th>
            <th>Rok urodzenia</th>
            <th>Książki</th>
            <th>Edycja</th>
            <th>Usuwanie</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${authors}" var="author" varStatus="stat">
            <tr>
                <td>${stat.index + 1}</td>
                <td>${author.firstName}</td>
                <td>${author.lastName}</td>
                <td>${author.pesel}</td>
                <td>${author.email}</td>
                <td>${author.yearOfBirth}</td>
                <td><c:forEach items="${author.books}" var="book" varStatus="stat">
                    "${book.title}",
                </c:forEach>
                </td>

                <td><a href="/authors/form/update/${author.id}">Update</a></td>
                <td><a href="/authors/form/confirmDelete/${author.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
