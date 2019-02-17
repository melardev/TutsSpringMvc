<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Spring MVC Basics"/>
<c:import url="/includes/header.jsp"/>

<form role="form" method="post">
    <div class="form-group">
        <label for="color">Choose color</label> <input id="color" type="color"
                                                       name="color"
                                                       value="${color}"/>
        <br/>
        <label>Current selected:${color}
        </label>
    </div>
    <button type="submit" class="btn btn-info">Submit</button>
</form>
