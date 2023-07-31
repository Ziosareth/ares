package com.lottomatica.lia.ares.workflow;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class AresGatewayTest {

    AresGateway aresGateway = new AresGateway(WebClient.builder().build());

    public static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.close();
    }

    @InstancioSource
    @ParameterizedTest
    void exchange(String body) {
        String endpoint = "http://localhost:8080/ares/test";

        mockWebServer.enqueue(new MockResponse()
                .setBody("a test"));

        Mono<String> exchange = aresGateway.exchange(HttpMethod.POST.name(), endpoint, body);
        StepVerifier.create(exchange).expectSubscription()
                .expectNext("a test")
                .expectComplete()
                .verify();

    }
}