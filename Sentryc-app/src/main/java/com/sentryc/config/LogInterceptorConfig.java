package com.sentryc.config;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogInterceptorConfig implements HandlerInterceptor{
	@Override
	   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			UUID uuid = UUID.randomUUID();
			request.setAttribute("start", System.currentTimeMillis());
			request.setAttribute("request-id", uuid);
			log.info("{} - prehandle calling for uri {}", uuid, request.getRequestURI());
			return true;
	   }
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		  log.info( "{} - Post Handle method and response in {}ms for uri {}", request.getAttribute("request-id"),  System.currentTimeMillis() - (long) request.getAttribute("start"), request.getRequestURI() );
	   }
	   @Override
	   public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		   log.info( "{} - after completion completed in {}ms for uri {}", request.getAttribute("request-id"),System.currentTimeMillis() - (long) request.getAttribute("start"), request.getRequestURI() );
	   }
}
