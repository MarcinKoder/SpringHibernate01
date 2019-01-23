<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 23.01.2019
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Errors</title>
</head>
<body>
<c:forEach items="${errors}" var="error" varStatus="stat">
        ${error.propertyPath} : ${error.message} <br/>
</c:forEach>
</body>
</html>
