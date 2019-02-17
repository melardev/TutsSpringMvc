<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Hello World Spring MVC"/>
<c:import url="/includes/header.jsp"/>

${message}
<h3>Metadata</h3>
<a href="<c:url value="/metadata/handler_mappings" />">Handler Mappings</a> |
<a href="<c:url value="/metadata/handler_adapters" />">Handler Adapters</a> |
<a href="<c:url value="/metadata/property_editors" />">Property Editors</a> |
<a href="<c:url value="/metadata/http_message_converters" />">Http Message Converters</a> |
<a href="<c:url value="/metadata/root_context_beans" />">Root Context Beans</a> |
<a href="<c:url value="/metadata/dispatcher_context_beans" />">Dispatcher Context Beans</a>
<a href="<c:url value="/metadata/context-info" />">Beans in Root and Dispatcher Context</a>
<c:import url="/includes/footer.jsp"/>
