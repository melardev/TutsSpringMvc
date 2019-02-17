<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Hello World Spring MVC"/>
<c:import url="/includes/header.jsp"/>

<h6>Upload and Download</h6>
<a href="<c:url value="/upload_download/upload"/>">Upload</a>|
<a href="<c:url value="/upload_download/download"/>">Upload</a>

<c:import url="/includes/footer.jsp"/>