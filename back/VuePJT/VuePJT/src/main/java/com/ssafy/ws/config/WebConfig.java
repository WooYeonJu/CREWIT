package com.ssafy.ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ws.interceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).excludePathPatterns(
        		"/userapi/**", "/userapi/user",
                "/exerciseapi/**", "/exerciseplaceapi/**", "/mapcommentapi/comment/**",
                "/api-crew/crew/list",
                "/swagger-ui/**", "/v3/api-docs/**",
                "/src/main/resources/static/**"
        ).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**").allowedOriginPatterns("*") // '*' 대신 패턴 사용
                .allowedMethods("*").allowedHeaders("*");
    }
}
