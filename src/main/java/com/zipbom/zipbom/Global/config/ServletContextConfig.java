package com.zipbom.zipbom.Global.config;


import com.zipbom.zipbom.Global.interceptor.RestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ServletContextConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("jwt-auth-token"); // 클라이언트가 헤더를 추출 가능하도록 설정
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 인터셉터 등록
        registry.addInterceptor(restInterceptor())
                .addPathPatterns("/**") // Interceptor가 적용될 경로
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/csrf")
                .excludePathPatterns("/exception/**"); // Interceptor가 적용되지 않을 경로
    }

    @Bean
    public RestInterceptor restInterceptor() {
        return new RestInterceptor();
    }
}