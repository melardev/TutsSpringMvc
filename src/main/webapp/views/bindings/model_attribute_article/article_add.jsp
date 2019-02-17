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
<c:set var="title" scope="request" value="Spring MVC Binding"/>
<c:import url="/includes/header.jsp"/>

<h2>${header_tut}</h2>
<br/>
<h3>${subheader}</h3>
<br/>
${message}
<c:if test="${errors != null}">
    <div class="alert alert-danger">
        <c:forEach items="${errors}" var="error">
            ${error} <br/>
        </c:forEach>
    </div>

</c:if>
<form method="post" action="<c:url value="/bindings/add_article_no_model_attribute" />">
    <div class="form-group row">
        <label for="title1" class="col-sm-2 col-form-label">Title: </label>
        <div class="col-sm-10">
            <input class="form-control" id="title1" type="text" name="title"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="body1" class="col-sm-2 col-form-label">Body: </label>
        <div class="col-sm-10">
            <input class="form-control" id="body1" type="text" name="body">
        </div>
    </div>
    <button class="btn btn-primary" type="submit">Add Article</button>
</form>
<br>
<br>
<form method="post" action="<c:url value="/bindings/add_article_model_attribute" />">
    <div class="form-group row">
        <label for="title2" class="col-sm-2 col-form-label">Title: </label>
        <div class="col-sm-10">
            <input class="form-control" id="title2" type="text" name="title"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="body2" class="col-sm-2 col-form-label">Body: </label>
        <div class="col-sm-10">
            <input class="form-control" id="body2" type="text" name="body">
        </div>
    </div>
    <button class="btn btn-primary" type="submit">Add Article</button>
</form>

<br/>
<br/>
<form:form method="post" action='/bindings/add_article_model_attribute' modelAttribute="article">
    <form:errors cssStyle="color: #ff0000;" path="*"/>

    <div class="form-group row">
        <form:label path="title" cssClass="col-sm-2 col-form-label">Title: </form:label>
        <div class="col-sm-10">
            <form:errors cssStyle="color: #ff0000;" path="title"/>
            <form:input path="title" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="title" cssClass="col-sm-2 col-form-label">Body: </form:label>
        <div class="col-sm-10">
            <form:errors cssStyle="color: #ff0000;" path="body"/>
            <form:input path="body" cssClass="form-control"/>
        </div>
    </div>
    <button class="btn btn-primary" type="submit">Add Article</button>
</form:form>

<c:import url="/includes/footer.jsp"/>