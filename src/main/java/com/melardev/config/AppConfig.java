package com.melardev.config;

import com.melardev.controllers.BeanNameDemoController;
import com.melardev.controllers.ControllerFromInterface;
import com.melardev.controllers.MyHttpRequestHandler;
import com.melardev.listeners.EventListener;
import com.melardev.services.ArticlesXmlService;
import com.melardev.services.UserService;
import com.melardev.validators.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Properties;


@Configuration
@Import(MyWebMvcConfigurer.class)
public class AppConfig {

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //resolver.setMaxUploadSizePerFile(10240); //10Kb
        resolver.setDefaultEncoding("UTF-8");
        //resolver.setResolveLazily(true);
        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("errors");
        return messageSource;
    }


    @Autowired
    WebApplicationContext webApplicationContext;

    /**
     * Tutorial on Thymyleaf integration, SpringResourceTemplateResolver is the class used for views resolution
     *
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver getTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(webApplicationContext);
        templateResolver.setOrder(9); //Higher the order, lower priority
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    /**
     * The TemplateEngine enables processors, expressions, etc.
     * Manages caching, template resolvers, etc.
     *
     * @return
     */
    //@Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    //@Bean
    ThymeleafViewResolver configureViewResolvers() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setViewNames(new String[]{"*.html"});
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(10); //Higher the order, lower priority
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/views/");
//        viewResolver.setViewNames("*.jsp");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public EventListener getAppEventListener() {
        return new EventListener();
    }

    /**
     * Used on BeanHandlerMapping Tutorial, I prove how a Url path is mapped to a Bean,
     * This is only the first of a 2 steps process:
     * 1. HandlerMapping wich maps a Url to a Handler( in this case a Url to a Bean)
     * 2. The execution of the handler. This step is done through HandlerAdapter, please watch HandlerAdaoter Tutorial
     *
     * @return
     */
    @Bean("/url_to_bean_name")
    public BeanNameDemoController getBeanNameController() {
        return new BeanNameDemoController();
    }

    /**
     * Used on HandlerAdapter Tutorial, first we map a url to a bean name as:
     * localhost/http_request_handler_adapter to MyHttpRequestHandler(since it has the same name as the URL)
     * now Spring MVC has to find which is the strategy to executed the handler.
     * There is a SimpleControllerHandlerAdapter that has the following strategy:
     * Checks if Handler(our bean) extends from HttpRequestHandler, if it does, it calls handle(req, res)
     *
     * @return
     */
    @Bean("/http_request_handler_adapter")
    public MyHttpRequestHandler getHttpRequestHandler() {
        return new MyHttpRequestHandler();
    }

    @Bean("/controller_from_interface")
    public ControllerFromInterface cotnrollerFromInterface() {
        return new ControllerFromInterface();
    }

    /**
     * Used in File Upload Tutorial
     *
     * @return
     */
    @Bean
    public FileValidator getFileUploadValidator() {
        return new FileValidator();
    }

    @Bean
    public ArticlesXmlService getArticlesXmlService() {
        return new ArticlesXmlService();
    }

    @Bean("articleView")
    public View articleView() {
        MarshallingView marshallingView = new MarshallingView();
        marshallingView.setMarshaller(getMarshaller());
        return marshallingView;
    }

    @Bean
    public Marshaller getMarshaller() {
        //spring-oxm
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // add manually which classes to be added.
        // By adding ArticlesXml, its field List<ArticleXml> will be included as well.
        // marshaller.setClassesToBeBound(ArticlesXml.class);
        marshaller.setPackagesToScan("com.melardev.models"); // more convenient scans packages you want and auto adds them
        return marshaller;
        // XStreamMarshaller marshaller = new XStreamMarshaller();
        // marshaller.setSupportedClasses();
        // marshaller.setAutodetectAnnotations(true); // still works without, but weird names may be rendered
        // return marshaller;
    }


    @Bean
    public ViewResolver getBeanResolver() {
        BeanNameViewResolver beanResolver = new BeanNameViewResolver();
        beanResolver.setOrder(1);
        return beanResolver;
    }


    /*
    In the servlet exception handling tutorial we saw Servlets may handle error Http codes as well
    Java exceptions, HandlerExceptionResolver only deals with Java exceptions
     */
    @Bean
    HandlerExceptionResolver customExceptionHandler() {
        // http://www.logicbig.com/how-to/spring-mvc/spring-customizing-default-error-resolver/
        // Exceptions are handled by default by DefaultHandlerExceptionResolver
        SimpleMappingExceptionResolver s = new SimpleMappingExceptionResolver();
        Properties p = new Properties();
        p.setProperty(NoHandlerFoundException.class.getName(), "error-page");
        s.setExceptionMappings(p);
        // default status code would be 200 unless overwritten
        s.addStatusCode("error-page", HttpStatus.NOT_FOUND.value());
        // This exception resolver will be processed before default ones
        s.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return s;

        /*return new HandlerExceptionResolver() {
        http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/handler-exception-resolver/
            @Nullable
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
                ModelAndView model = new ModelAndView("error-page");
                model.addObject("exceptionType", ex);
                model.addObject("handlerMethod", handler);
                return model;
            }
        };*/
    }


    @Bean
    public UserService userService() {
        return new UserService();
    }
}
