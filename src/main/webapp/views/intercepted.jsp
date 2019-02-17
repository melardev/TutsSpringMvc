<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Spring MVC Basics"/>
<c:import url="/includes/header.jsp"/>

${message}<br>
<a href="<c:url value="/intercepted/request_attributes" />">@RequestAttribute</a>


<c:import url="/includes/footer.jsp"/>