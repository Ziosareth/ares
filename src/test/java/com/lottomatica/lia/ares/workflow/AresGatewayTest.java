package com.lottomatica.lia.ares.workflow;

import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class AresGatewayTest {

    @InjectMocks
    AresGateway aresGateway;
    @Mock
    WebClient webclient;


    @InstancioSource
    @ParameterizedTest
    void exchange(String method, String endpoint, String body) {
        Mono<String> exchange = aresGateway.exchange(method, endpoint, body);
        StepVerifier.create(exchange).expectSubscription()
                .expectNext("It's A Me MaRiO0!11!11!")
                .expectComplete()
                .verify();

    }
}