<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 16:20
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
    <title>All books</title>
</head>
<body>
<div>
    <table>
        <thead>
        <tr>
            <th>Lp.</th>
            <th>Tytuł</th>
            <th>Opis</th>
            <th>Ocena</th>
            <th>Strony</th>
            <th>Autor/Autorzy</th>
            <th>Wydawca</th>
            <th>Edycja</th>
            <th>Usuwanie</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="book" varStatus="stat">
            <tr>
                <td>${stat.index + 1}</td>
                <td>${book.title}</td>
                <td>${book.description}</td>
                <td>${book.rating}</td>
                <td>${book.pages}</td>
                <td><c:forEach items="${book.authors}" var="author" varStatus="stat">
                    ${author.firstName} ${author.lastName},
                </c:forEach>
                </td>
                <td>${book.publisher.name}</td>
                <td><a href="/books/form/update/${book.id}">Update</a></td>
                <td><a href="/books/form/confirmDelete/${book.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
