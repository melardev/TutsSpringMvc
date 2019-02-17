package com.melardev.interceptors;

import com.melardev.config.MyWebMvcConfigurerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class MyInterceptor2 extends WebContentInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        //return super.preHandle(request, response, handler);
/*
        String vuid = CookieUtil.getOrCreateVuid(request, response);
        String key = vuid + WebConstants.FRAMEWORK_PROXY_USER;
        Class<Object> c = null;
        MemberInfo member = (MemberInfo) iRedisService.getRedisResult(key, c);
        if (member != null) {

            return true;
        } else {
            try {
                response.sendRedirect(request.getContextPath()+"/login/view.do");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        */
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void setCacheMappings(Properties cacheMappings) {
        super.setCacheMappings(cacheMappings);
        cacheMappings.put("/**/*.js", 86400);
        cacheMappings.put("/**/*.css", 86400);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
