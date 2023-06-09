package com.lottomatica.lia.ares.workflow;

import reactor.core.publisher.Mono;

public interface IAresGateway {

    Mono<String> exchange(String method, String url, String requestBody);

}
