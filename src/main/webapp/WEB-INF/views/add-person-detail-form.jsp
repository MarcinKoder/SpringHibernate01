<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: XxX
  Date: 22.01.2019
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Person Details Form</title>
</head>
<body>
<div class="container">
    <div class="row center">
        <form:form method="post" modelAttribute="personDTO">
            Login: <form:input path="login"/><br/>
            Email: <form:input path="email" type="email"/><br/>
            Password: <form:password path="password"/><br/>
            First Name: <form:input path="firstName"/><br/>
            Last Name: <form:input path="lastName"/><br/>
            Gender:
            Male <form:radiobutton path="gender" value="M"/>
            Female <form:radiobutton path="gender" value="F"/><br/>
            Choose country:
            <form:select path="country">
                <form:option value="-" label="--Please Select--"/>
                <form:options items="${countries}"/>
            </form:select><br/>
            Notes: <br/>
            <form:textarea path="notes"/><br/>
            Mailing list:
            <form:checkbox path="mailingList"/><br/>
            Skills:<br/> <form:select path="programmingSkills" items="${skills}" multiple="true"/><br/>
            Hobbies:<br/> <form:checkboxes path="hobbies" items="${hobbies}"/><br/>
            <input type="submit" value="Zapisz"/>
            <input type="reset" value="Wyczyść"/>
        </form:form>
    </div>
</div>

</body>
</html>
