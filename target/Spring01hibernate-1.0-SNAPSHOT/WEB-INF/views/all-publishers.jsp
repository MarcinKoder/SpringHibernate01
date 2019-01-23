<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 20:54
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
    <title>All publishers</title>
</head>
<body>
<div>
<table>
    <thead>
    <tr>
        <th>Lp.</th>
        <th>Nazwa</th>
        <th>NIP</th>
        <th>REGON</th>
        <th>Edycja</th>
        <th>Usuwanie</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${publishers}" var="publisher" varStatus="stat">
        <tr>
            <td>${stat.index + 1}</td>
            <td>${publisher.name}</td>
            <td>${publisher.nip}</td>
            <td>${publisher.regon}</td>
            <td><a href="/publishers/form/update/${publisher.id}">Update</a></td>
            <td><a href="/publishers/form/confirmDelete/${publisher.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
