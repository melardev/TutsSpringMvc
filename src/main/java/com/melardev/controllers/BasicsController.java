package com.melardev.controllers;

import com.melardev.annotations.NoContent;
import com.melardev.models.User;
import org.springframework.beans.factory.BeanExpressionException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/basics")
public class BasicsController {

    // Default method for the Controller
    //@RequestMapping("")
    //@RequestMapping()
    @RequestMapping
    public String getHome() {
        return "basics";
    }

    // HEAD will be supported as well
    // Only mapped if Accept: application/json or */*; You could negate , example : !text/plain
    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getJsonHelloWorld() {
        return "{\"status\": \"Alive\"}";
    }

    // RequestMapping tutorial

    @RequestMapping(path = {"simple_txt_plain", "simple_txt_plain/*"}, produces = MediaType.TEXT_PLAIN_VALUE)
    //@ResponseBody If you send a view it will override your produces with text/html
    public String getSimpleText(HttpServletResponse response, Model model) {
        response.setContentType("text/plain"); // No effect, it is overriden
        model.addAttribute("message", "Simple text/plain hit");
        return "basics";
    }

    //APPLICATION_JSON_VALUE
    @RequestMapping(value = "/get_json", produces = {"text/plain", MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public String getJson(Model model) {
        return "{'message': 'Hello world'}";
    }

    @RequestMapping(path = "/get_post", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/plain")
    public String getAndPost(HttpServletRequest request, @RequestParam(value = "username", required = false) String username,
                             @RequestBody(required = false) String bodyForm, Model model) {
        if ("post".equalsIgnoreCase(request.getMethod()))
            model.addAttribute("message", "POST Method; username: " + username + "; FormBody: " + bodyForm);
        else if ("get".equalsIgnoreCase(request.getMethod()))
            model.addAttribute("message", "GET Method");
        return "basics";
    }

    @RequestMapping("*")
    public String getNotFound(Model model) {
        model.addAttribute("message", "Not Found");
        return "basics";
    }


    @GetMapping("/get_mapping")
    public String get(Model model) {
        model.addAttribute("message", "GetMapping in action");
        return "basics";
    }

    // Only handle if Accept text/html and Content Type text/plain
    //headers = {"Accept=text/html", "AnotherHeader=SomeOtherValue"} insensitive
    @RequestMapping(name = "Produces plain text", value = "/produces_text", headers = "admin")
    @ResponseBody
    public String producesPlain() {
        return "Plain text my friend";
    }

    @GetMapping("/sections/{section}")
    public String variablePathSimple(@PathVariable("section") String sectionPage, Model model) {
        // all RequestParam are required except those explicitly stated as required=false and
        // those with a defaultValue. DefaultValue acts when is not provided OR empty
        model.addAttribute("message", "You requested to view the " + sectionPage + " page");
        return "basics";
    }

    @GetMapping("/cars/{brand:[a-zA-Z]+}")
    public String variablePath(@PathVariable String brand, Model model) {
        // all RequestParam are required except those explicitly stated as required=false and
        // those with a defaultValue. DefaultValue acts when is not provided OR empty
        model.addAttribute("message", "You requested to view the " + brand + " cars page");
        return "basics";
    }

    @GetMapping(value = "/cars")
    public String simpleGetQuery(@RequestParam("brand") String carBrand, Model model) {
        model.addAttribute("message", String.format("showing cars from %s", carBrand));
        return "basics";
    }

    @GetMapping(value = "/cars/{id:[\\d]+}")
    public String variablePathAndQuery(@PathVariable("id") int carId, @RequestParam String brand, Model model) {
        model.addAttribute("message", String.format("showing car from %s; car id is %d",
                brand, carId));
        return "basics";
    }

    //GetMapping is the same as @RequestMapping(method=RequestMethod.GET)
    @GetMapping(value = "/all/{path1}/{path2}")
    public String getAllQueryAndPath(@PathVariable Map<String, String> params, @RequestParam Map<String, String> queries, Model model) {
        StringBuilder sb = new StringBuilder();
        sb.append("Path Parameters:<br>");
        params.forEach((k, v) -> {
            sb.append(k + "=" + v + "<br>");
        });
        sb.append("<br>");
        sb.append("Query parameters:<br>");
        queries.forEach((k, v) -> {
            sb.append(k + "=" + v + "<br>");
        });
        sb.append("<br>");
        model.addAttribute("message", sb.toString());
        return "basics";
    }


    @GetMapping(value = "/optional")
    public String simpleGetQuery(@RequestParam("id") Optional<Integer> id, Model model) {
        if (id.isPresent())
            model.addAttribute("message", "You entered " + id.get());
        else
            model.addAttribute("message", "you entered nothing");
        return "basics";
    }


    /*
    //This will throuw an exception
    @RequestMapping(value = "/ambiguous")//,params = "some1")
    @ResponseBody
    public String someAmbiguous1(@RequestParam String some1) {
        //Not specifying parameter value will only work if compiled with debugging information, or -paramjeters flag on Java 8
        // because it will preserve the parameter name, otherwise it will not
        return "hey";
    }

    @RequestMapping(value = "/ambiguous")//, params = "some2")
    @ResponseBody
    public String someAmbiguous2(@RequestParam String some2) {
        return "hey";
    }
    */


    // Supported Argument types
    @RequestMapping(value = "/supported_arg_types", method = {RequestMethod.GET, RequestMethod.POST})
    // @ResponseBody If you don't use this, then the string will be used as view name, and while rendering it
    // getOutputStream() will be called again throwin an exception. So don't ask for OutputStream if you return a view name
    public String getSupportedArgTypes(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                       Locale locale, WebRequest webRequest, InputStream is, //OutputStream os,
                                       HttpMethod method, Principal principal, HttpEntity<?> httpEntity,
                                       Map<String, String> outMap, ModelMap outModelMap, Model model,
                                       SessionStatus sStatus, RedirectAttributes rattrs,
                                       UriComponentsBuilder uri, ModelAndView modelAndView) {
        String str = String.format("Request Method %s<br>Response Content-Type %s<br>" +
                        "Session id %s<br>Locale Country <br>ContextPath from WebRequest %s<br> Method %s<br>" +
                        "HttpEntity as String %s", request.getMethod(), response.getContentType(), session.getId(), locale.getCountry()
                , webRequest.getContextPath(), method.name(), httpEntity.toString());

        //, httpEntity.toString());
        model.addAttribute("message", str);

        outMap.put("message2", "another Message in the Map");
        outModelMap.addAttribute("message3", "Inside outModelMap");

        /*modelAndView.setViewName("index");
        modelAndView.getModelMap().addAttribute("message4", "some msg")
                .addAttribute("message5", "some message");
        */

        return "basics";
    }

    @GetMapping("/supported_ret_types/response_body")
    @ResponseBody
    public String getSupportedRetTypeResponseBody() {
        return "Some String";
    }

    @GetMapping("/supported_ret_types/http_entity")
    public HttpEntity<String> getSupportedRetTypeHttpEntity() {
        //return new HttpEntity<>("<b>Response as HttpEntity</b>");
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList(MediaType.TEXT_HTML_VALUE));
        return new HttpEntity<>("<b>Response as HttpEntity</b>", headers);
    }

    @GetMapping("/supported_ret_types/response_entity")
    public ResponseEntity<String> getSupportedRetTypeResponseEntity() {
  /*      return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .contentType(MediaType.TEXT_PLAIN)
                .header("SomeHeaderName", "HeaderValue")
                .eTag("122232")
                .lastModified(ZonedDateTime.of(LocalDateTime.of(2017, 12, 12, 16, 21, 00)
                        , ZoneId.of("GMT")).toInstant().toEpochMilli())
                .body("Some");
*/
        if (new Random().nextBoolean())
            return new ResponseEntity<String>(new String("<b>Welcome!!</b>"), HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);


    }

    @GetMapping("/supported_ret_types/response_headers")
    public HttpHeaders getSupportedRetTypeViewEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("Playlist", "JAX with Jersey");
        headers.add("Playlist", "Spring MVC");
        headers.set("Tutorial", "Jersey Tutorial");
        headers.set("Tutorial", "Spring MVC Supported Return Types");
        return headers;
    }


    @GetMapping("/supported_ret_types/void_response_status")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Alive")
    public void getSupportedRetTypeHttpStatus() {
    }

    @GetMapping("/supported_ret_types/model")
    public String getSupportedRetTypeViewEntity(Model model) {
        model.addAttribute("message", "Some attribute in the model");
        return "basics";
    }

    @GetMapping("/supported_ret_types/model_view")
    public ModelAndView getSupportedRetTypeViewEntity(ModelAndView modelAndView) {

        modelAndView.setViewName("basics");
        modelAndView.addObject("message", "Message 1");
        return modelAndView;
       /*
        ModelAndView mav = new ModelAndView("basics");
        modelAndView.setStatus(HttpStatus.NO_CONTENT);
        mav.addObject("message", "Message 1");
        return mav;
        */
    }

    // Async request processing since Servlet 3.0
    @GetMapping("/supported_ret_types/response_streaming_response_body")
    public StreamingResponseBody getSupportedRetTypeStreamingResponseBody() {
        // Part of the asynchronous request processing feature available since Servlet 3.0
        // also supported as the body of ResponseEntity
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                outputStream.write(new String("<b>Response from StreamingResponseBody</b>").getBytes());
                outputStream.flush();
            }
        };
    }

    @GetMapping("/supported_ret_types/response_emitter")
    public ResponseBodyEmitter getSupportedRetTypeResponseBodyEmitter() {
        // Part of the asynchronous request processing feature available since Servlet 3.0
        // also supported as the body for ResponseEntity (useful when you want to send custom headers
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // The appropiate HttpMessageConverter for each object is called
                    emitter.send("This year is ", MediaType.TEXT_PLAIN);
                    emitter.send(new Integer(2017), MediaType.TEXT_PLAIN);

                } catch (IOException e) {
                    emitter.completeWithError(e);
                    return;
                }
                emitter.complete();
            }
        });

        return emitter;
    }

    @GetMapping("/supported_ret_types/sse_emitter")
    public SseEmitter getSupportedRetTypeSseEmitter() {
        SseEmitter emitter = new SseEmitter();
        try {//Content-Type would be text/event-stream
            emitter.send("2233");
            emitter.send(22323);
            emitter.send(emitter.event().id("SomeId"));
            emitter.complete();
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @GetMapping("/supported_ret_types/deferred_result")
    public @ResponseBody
    DeferredResult<String> getSupportedRetTypeDeferredResult() {
        final DeferredResult<String> deferredResult = new DeferredResult<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            deferredResult.setResult("test async result");
        }).start();


        return deferredResult;
    }

    @GetMapping("/supported_ret_types/callable")
    public @ResponseBody
    Callable<String> getSupportedRetTypeCallable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "async result";
            }
        };

        return callable;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/controller_advice_exception")
    public String throwBeanExpressionException() {
        throw new BeanExpressionException("Hopefully it triggers @ControllerAdivce @ExceptionHandler");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trigger_mismatch")
    public String triggerMismatch(@RequestParam("m") int date) {
        return "basics";
    }


    @GetMapping("/cookies")
    public String getCookie(@CookieValue(value = "color", defaultValue = "#fff") String color, HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("color") Optional<String> colorChoosen, Model model) {
        //Cookie[] cookies = request.getCookies();
        //or
        /*Cookie cookie = WebUtils.getCookie(request, "color");
        if (cookie != null)
            color = cookie.getValue();
            */
        model.addAttribute("color", color);
        return "cookies";
    }

    @PostMapping("/cookies")
    public String setCookie(HttpServletResponse response,
                            @RequestParam("color") Optional<String> colorChoosen, Model model) {
        String color = "#fff";
        if (colorChoosen.get() != null) {
            color = colorChoosen.get();
            response.addCookie(new Cookie("color", color));
        }
        model.addAttribute("color", color);
        return "cookies";
    }

    @NoContent("/no-content")
    public void composedAnnotations() {

    }

    @RequestMapping("sessions")
    public String sessionsDemo(Model model) {
        model.addAttribute("message", "Not Found");
        return "basics";
    }

    public String webUtilsDemo(Model model) {
        //WebUtils.getCookie();
        return "basics";
    }

    @GetMapping("/thymyleaf")
    public String thymyleafDemo(Model model) {
        model.addAttribute("user", new User("Melar", "Dev"));
        return "thymeleaf.html";
    }
}
