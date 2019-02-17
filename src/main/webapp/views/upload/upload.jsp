<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Hello World Spring MVC"/>
<c:import url="/includes/header.jsp"/>
<h6>Upload Demo 1</h6>
<form enctype="multipart/form-data" method="post" action="<c:url value="/upload_download/upload1"/>">
    <input type="file" name="upload_file"/>
    <br>
    <input type="submit" value="Upload File" class="btn btn-primary"/><br/>
</form>
<br>
<br>
<h6>Upload Demo 2</h6>

<form enctype="multipart/form-data" method="post" action="<c:url value="/upload_download/upload2"/>">
    <input type="file" name="upload_file"/>
    <br>
    <input type="submit" value="Upload File" class="btn btn-primary"/><br/>
</form>
<br>
<br>
<h6>Upload Demo 3</h6>

<form:form modelAttribute="fileUploadForm" method="POST" enctype="multipart/form-data"
           action="/upload_download/upload3">
    <form:errors cssClass="alert alert-danger" path="file"/>
    <br/>
    <form:input type="file" path="file"/>
    <br><br>
    <input type="submit" value="Upload File" class="btn btn-primary"/><br/>
</form:form>
<c:import url="/includes/footer.jsp"/>