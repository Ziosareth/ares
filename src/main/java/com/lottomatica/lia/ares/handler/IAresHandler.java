package com.lottomatica.lia.ares.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IAresHandler {

    Mono<ServerResponse> workflowHandler(ServerRequest serverRequest);

}
