package com.gateway.filter;

import com.gateway.external.impl.AuthControllerFeignClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private AuthControllerFeignClient authControllerFeignClient;
    @Autowired
    private JwtService jwtService;
    public AuthenticationFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        logger.info("AuthenticationFilter - Inside GatewayFilter method");
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing Authorization Header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                    logger.info("authHeader Token = " + authHeader);
                }
                try {
                    jwtService.validateToken(authHeader);
                }catch (Exception e){
                    System.out.println("Invalid Access!!!");
                    throw new RuntimeException("Unauthorized access to application!!!");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
