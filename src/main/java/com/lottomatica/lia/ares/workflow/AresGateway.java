package com.lottomatica.lia.ares.workflow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AresGateway implements IAresGateway {

    private final WebClient webClient;

    @Override
    public Mono<String> exchange(String method, String url, String requestBody) {
        return Mono.just("It's A Me MaRiO0!11!11!");
    }
}
