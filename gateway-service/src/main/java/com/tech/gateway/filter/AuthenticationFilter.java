package com.tech.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        logger.info("path is "+path);

        if (path.startsWith("/auth")) {
            logger.info("skipping auth check for auth-service");
            return chain.filter(exchange);  // Skip auth check for auth-service
        }
        return chain.filter(exchange);

//        ServerHttpRequest request = exchange.getRequest();
//        String authHeader = request.getHeaders().getFirst("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//
//        String token = authHeader.substring(7);
//
//        // Call auth-service to validate token (simplified, use WebClient for async call)
//        org.springframework.web.reactive.function.client.WebClient webClient = org.springframework.web.reactive.function.client.WebClient.create("http://auth-service");
//        return webClient.get()
//                .uri("/validate?token=" + token)
//                .retrieve()
//                .toBodilessEntity()
//                .flatMap(response -> {
//                    if (response.getStatusCode().is2xxSuccessful()) {
//                        return chain.filter(exchange);
//                    } else {
//                        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
//                        return exchange.getResponse().setComplete();
//                    }
//                })
//                .onErrorResume(e -> {
//                    exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
//                    return exchange.getResponse().setComplete();
//                });
     }
}


