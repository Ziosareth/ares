package com.lottomatica.lia.ares.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lottomatica.lia.ares.workflow.IWorkflowManager;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AresEndpointTest {


    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    IWorkflowManager workflowManager;

    @Test
    @DisplayName("Ares custom endpoint returns  response")
    void testCustomEndpoint() {
        String payoutEndpoint = "/payout";
        String bodyMap = Instancio.create(String.class);
        when(workflowManager.executeWorkflow(payoutEndpoint, bodyMap)).thenReturn(Mono.just(bodyMap));
        webTestClient.post().uri(payoutEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyMap))
                .exchange()
                .expectBody(String.class)
                .isEqualTo(bodyMap);
    }


}