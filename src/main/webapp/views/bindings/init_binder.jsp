<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="title" scope="request" value="Init Binder Demos"/>
<c:import url="/includes/header.jsp"/>


<hr>
<h6>Init Binder</h6>
<a href="<c:url value="/bindings/init_binder/demo1" />">Basics</a> |
<a href="<c:url value="/bindings/init_binder/demo2" />">Currency Formatter</a>

<c:import url="/includes/footer.jsp"/>