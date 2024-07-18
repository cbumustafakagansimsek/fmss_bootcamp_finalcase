package com.patika.gateway.filter;

import com.patika.gateway.exception.AuthException;
import com.patika.gateway.exception.ExceptionMessage;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new AuthException("Missing authorization information");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new AuthException("Incorrect authorization structure");
            }

            return webClientBuilder.build()
                    .post()
                    .uri("http://user-service/api/v1/auth/validateToken?token=" + parts[1])
                    .retrieve().bodyToMono(Payload.class)
                    .onErrorMap(WebClientResponseException.class,ex -> new AuthException(ex.getResponseBodyAs(ExceptionMessage.class).getMessage()))
                    .map(payload -> {
                        routeControlByRole(exchange,payload.getRole());
                        exchange.getRequest()
                                .mutate()
                                .header("x-auth-user-id",payload.getId().toString() );
                        return exchange;
                    }).flatMap(chain::filter);


        };
    }

    public void routeControlByRole(ServerWebExchange exchange,AuthRole role){
        if (exchange.getRequest().getPath().toString().contains("/api/v1/payment")) {
            if (!AuthRole.INITIAL.equals(role) && !AuthRole.SUBSCRIPTED.equals(role)) {

                throw  new AuthException("Unauthorized ");
            }
        } else if (exchange.getRequest().getPath().toString().contains("/api/v1/ads/secure")) {

            if (!AuthRole.SUBSCRIPTED.equals(role)) {
                throw  new AuthException("2Unauthorized role:"+role);
            }
        }

    }
    public static class Config {
    }
}
