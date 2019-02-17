<%--
  Created by IntelliJ IDEA.
  User: melardev
  Date: 11/28/2017
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<frm:form action="register" method="post" commandName="user">
  <pre>
                  Name <frm:input path="name"/>
                       <frm:errors path="name" cssClass="error"/>

         Email address <frm:input path="emailAddress"/>
                       <frm:errors path="emailAddress" cssClass="error"/>

              Password <frm:password path="password"/>
                       <frm:errors path="password" cssClass="error"/>

                                  <input type="submit" value="Submit"/>
  </pre>
</frm:form>

</body>
</html>
