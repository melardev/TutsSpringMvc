<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="title" scope="request" value="Init Binder Demos"/>
<c:import url="/includes/header.jsp"/>

<hr>
<h6>Init Binder</h6>

<form:form modelAttribute="user" method="POST" enctype="multipart/form-data">

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


<h6>InitBinder Demo 2</h6>
<c:if test="${mockModel != null}">
    <spring:bind path="mockModel.salary">${mockModel.salary}</spring:bind>
</c:if>


<c:import url="/includes/footer.jsp"/>