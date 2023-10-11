package com.gateway.filter;

import com.gateway.config.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);

    @Autowired
    private RestTemplate template;
    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        logger.info("AuthenticationFilter - Inside GatewayFilter method");
        return (((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                System.out.println("exchange.getRequest = " + validator.isSecured.test(exchange.getRequest()));
                //header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authentication header.");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).stream().findAny().get();
                String usernameFromToken = null;
                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                    System.out.println("authHeader = " + authHeader);
                }
                try
                {
                    jwtService.validateToken(authHeader);
                }
                catch(Exception e)
                {
                    System.out.println("invalid access to application");
                    throw new RuntimeException("Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config
    {

    }

}