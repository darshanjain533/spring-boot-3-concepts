package com.sentryc.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorRegistryConfig implements WebMvcConfigurer {

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new LogInterceptorConfig() ).order(1);
        registry.addInterceptor( new AuthenticatorInterceptorConfig() ).order(2);
    }
}
