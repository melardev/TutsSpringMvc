<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="title" scope="request" value="Spring MVC Basics"/>
<c:import url="/includes/header.jsp"/>

Message: ${message}
<br>
Message2: ${message2}
<br>
Message3: ${message3}
<br>

<h3>@RequestMapping</h3>
<a href="<c:url value="/basics/status" />">Get Status as Json</a> |
<a href="<c:url value="/basics/simple_txt_plain" />">Produces</a> |
<a href="<c:url value="/basics/produces_text" />">Produces text</a> |
<a href="<c:url value="/basics/get_post" />">Get And Post</a>
<a href="<c:url value="/basics/get_json" />">Get Json</a> |
<a href="<c:url value="/basics/inexistent" />">Inexistent</a> |
<a href="<c:url value="/basics/get_mapping" />">Get Mapping</a> |
<br>
<h3>@RequestParam and @PathVariable</h3>
<a href="<c:url value="/basics/sections/about" />">Variable Path simple</a> |
<a href="<c:url value="/basics/cars/audi" />">Variable Path Param Regex</a> |
<a href="<c:url value="/basics/cars?brand=audi" />">Get parameter</a> |
<a href="<c:url value="/basics/cars/2?brand=audi" />">Path conversion Int And Get Param</a> |
<a href="<c:url value="/basics/all/to/some?query1=value1&query2=value2" />">All Query and Param Values</a>
<a href="<c:url value="/basics/optional" />">Optional</a>

<form method="post" action="<c:url value="/basics/get_post" />">
    <input type="text" name="username" placeholder="Enter your name">
    <button class="btn btn-primary" type="submit">Submit</button>
</form>

<br>
<h3>Supported Argument and Returned Data Types</h3>
<a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments"
   target="_blank">Official Doc</a> <br>
<a href="<c:url value="/basics/supported_arg_types" />">Get Supported Argument Types</a>
<br>
<h3>Supported Returned Data Types</h3>
<a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-return-types"
   target="_blank">Official Doc</a> <br>
<a href="<c:url value="/basics/supported_ret_types/response_body" />">ResponseBody</a> |
<a href="<c:url value="/basics/supported_ret_types/http_entity" />">HttpEntity</a> |
<a href="<c:url value="/basics/supported_ret_types/void_response_status" />">ResponseStatus</a> |
<a href="<c:url value="/basics/supported_ret_types/response_entity" />">ResponseEntity</a> |
<a href="<c:url value="/basics/supported_ret_types/response_headers" />">Response Headers</a> |
<a href="<c:url value="/basics/supported_ret_types/model" />">Model</a> |
<a href="<c:url value="/basics/supported_ret_types/model_view" />">Model And View</a>
<br>
<h6>Async Request Processing</h6>
<a href="<c:url value="/basics/supported_ret_types/response_streaming_response_body" />">StreamingResponseBody</a> |
<a href="<c:url value="/basics/supported_ret_types/response_emitter" />">Response Emiter</a> |
<a href="<c:url value="/basics/supported_ret_types/callable" />">Callable</a> |
<a href="<c:url value="/basics/supported_ret_types/deferred_result" />">Deferred Result</a>
<br>
<h6>Global Exception Handling</h6>
<a href="<c:url value="/basics/controller_advice_exception" />">ControllerAdvice</a>
<a href="<c:url value="/basics/trigger_mismatch?m=non_number" />">Trigger Mismatch</a>
<a href="<c:url value="/basics/trigger_mismatch" />">Trigger Missing Param</a>
<hr>
<h6>Cookies and Session</h6>
<a href="<c:url value="/basics/cookies" />">Cookies</a> |
<a href="<c:url value="/basics/session" />">Session</a> |

<h6>Context Info</h6>
<a href="<c:url value="/metadata/context-info" />">Context-Info</a>


<c:import url="/includes/footer.jsp"/>