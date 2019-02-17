<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="title" scope="request" value="Init Binder Demos"/>
<c:import url="/includes/header.jsp"/>

Message: ${message}
<hr>
<h6>Init Binder</h6>
<h6>InitBinder Demo 2</h6>
<c:if test="${mockModel != null}">
    <spring:bind path="mockModel.salary">${status.value}</spring:bind>
    <br>
    <spring:bind path="mockModel.money">${status.value}</spring:bind>
</c:if>


<c:import url="/includes/footer.jsp"/>