package com.lottomatica.lia.ares.workflow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class AresGateway implements IAresGateway {

    private final WebClient webClient;

    @Override
    public Mono<String> exchange(String method, String url, String requestBody) {
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        return webClient.method(httpMethod)
                .uri(url)
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class));
    }
}
