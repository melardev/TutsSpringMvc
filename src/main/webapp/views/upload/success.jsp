<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" scope="request" value="Hello World Spring MVC"/>
<c:import url="/includes/header.jsp"/>
<div class="alert alert-success">
    ${message}
</div>
<c:import url="/includes/footer.jsp"/>