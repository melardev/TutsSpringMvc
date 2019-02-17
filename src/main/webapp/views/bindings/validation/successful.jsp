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
<c:set var="title" scope="request" value="Spring MVC Hibernate Validation"/>
<c:import url="/includes/header.jsp"/>
<h3>Welcome ${user.firstName} ${user.lastName}</h3>
now you are registered, make sure to validate your account,
we sent to you an email, check it at ${user.email}</h3>

<c:import url="/includes/footer.jsp"/>