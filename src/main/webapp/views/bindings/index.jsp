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
<c:set var="title" scope="request" value="Spring MVC Binding Demos"/>
<c:import url="/includes/header.jsp"/>
<h3>@ModelAttribute</h3>
<a href="<c:url value="/bindings/model_attribute" />">Model Attribute Annotation</a>
<h3>Validation</h3>
<a href="<c:url value="/bindings/register_user" />">Hibernate Validation(Register user Demo)</a>
<h3>InitBinder</h3>
<a href="<c:url value="/bindings/init_binder" />">Init Binder</a>
<c:import url="/includes/footer.jsp"/>