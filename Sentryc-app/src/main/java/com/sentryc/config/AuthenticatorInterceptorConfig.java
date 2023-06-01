package com.sentryc.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticatorInterceptorConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        log.info( "{} - authenticating" , request.getAttribute("request-id") );
        return true;
    }

    @Override
    public void postHandle( HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception {
        log.info( "{} - authenticating Done" , request.getAttribute("request-id") );
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception exception) throws Exception {
      // after complete logic goes here....
    }
}