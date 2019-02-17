<%--
  Created by IntelliJ IDEA.
  User: melardev
  Date: 1/6/2018
  Time: 5:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="Spring MVC Validation with Hibernate Validation"/>
<c:import url="/includes/header.jsp"/>

<c:if test="${errors != null}">
    <div class="alert alert-danger">
        <c:forEach items="${errors}" var="error">
            ${error} <br/>
        </c:forEach>
    </div>
</c:if>

<form:form method="post" action='/bindings/register_user' modelAttribute="user">
    <form:errors cssStyle="color: #ff0000;" path="*"/>
    <hr>
    <div class="form-group row">
        <form:label path="firstName" cssClass="col-sm-2 col-form-label">First Name: </form:label>
        <div class="col-sm-10">
            <form:errors cssStyle="color: #ff0000;" path="firstName"/>
            <form:input path="firstName" cssClass="form-control" autocomplete="false"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="lastName" cssClass="col-sm-2 col-form-label">Last Name: </form:label>
        <div class="col-sm-10">
            <form:errors cssStyle="color: #ff0000;" path="lastName"/>
            <form:input path="lastName" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="birthDate" cssClass="col-sm-2 col-form-label">Birthdate: </form:label>
        <div class="col-sm-10">
            <form:errors cssStyle="color: #ff0000;" path="birthDate"/>
            <form:input path="birthDate" cssClass="form-control" autocomplete="false"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="email" cssClass="col-sm-2 col-form-label">Email: </form:label>
        <div class="col-sm-10">
            <form:errors cssStyle="color: #ff0000;" path="email" autocomplete="false"/>
            <form:input path="email" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-sm-10">
            <form:errors cssStyle="color: #ff0000;" path="acceptTerms"/>
            <form:checkbox path="acceptTerms" cssClass="form-control" label="Accept Terms of Service"/>
        </div>
    </div>
    <button class="btn btn-primary" type="submit">Register</button>
</form:form>

<c:import url="/includes/footer.jsp"/>