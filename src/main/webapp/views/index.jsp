<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Hello World Spring MVC"/>
<c:import url="/includes/header.jsp"/>

By the way, This view is shown from the Controller created in MyWebMvcConfigurer.java file
<br>
<a href="<c:url value="/basics" />">Basics</a> |
<a href="<c:url value="/bindings" />">Binding</a> |
<a href="<c:url value="/metadata" />">Metadata</a> |
<a href="<c:url value="/intercepted" />">Intercepted</a> |
<a href="<c:url value="/upload_download"/>">Upload & Download</a>

<c:import url="/includes/footer.jsp"/>
