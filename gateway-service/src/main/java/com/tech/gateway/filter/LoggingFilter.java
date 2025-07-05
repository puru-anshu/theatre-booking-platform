package com.tech.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        logger.info("Incoming request: {} {} from IP: {}",
                request.getMethod(),
                request.getURI(),
                request.getRemoteAddress());

        // Continue filter chain and log response after it's processed
        return chain.filter(exchange).doAfterTerminate(() -> {
            ServerHttpResponse response = exchange.getResponse();
            logger.info("Response status for {}: {}", request.getURI(), response.getStatusCode());
        });
    }

    @Override
    public int getOrder() {
        return -2; // Run before JwtAuthFilter (-1)
    }
}
