package com.ticket.interceptor;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Retrieve or generate your special note (JWT token)
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                String jwtToken = attributes.getRequest().getHeader("Authorization").replace("Bearer ", "");
                requestTemplate.header("Authorization", "Bearer " + jwtToken);
            }
        };
    }
}

