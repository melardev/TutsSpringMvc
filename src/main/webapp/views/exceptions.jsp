<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Spring MVC Basics"/>
<c:import url="/includes/header.jsp"/>

${message}
<br>

<h3>Exceptions</h3>
<a href="<c:url value="/exceptions/null_pointer" />">RuntimeException</a> |
<a href="<c:url value="/exceptions/rest_exception" />">Exceptions For Rest APIs</a> |
<a href="<c:url value="/exceptions/inexistent" />">Not Found</a> |
<a href="<c:url value="/exceptions/default_exception" />">Default</a>
<br>

<c:import url="/includes/footer.jsp"/>