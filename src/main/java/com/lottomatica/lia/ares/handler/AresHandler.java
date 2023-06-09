package com.lottomatica.lia.ares.handler;

import com.lottomatica.lia.ares.workflow.IWorkflowManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AresHandler implements IAresHandler{

    private final IWorkflowManager workflowManager;

    public Mono<ServerResponse> workflowHandler(ServerRequest serverRequest){
        String path = serverRequest.path(); //with this I'm going to retrieve the workflow to execute
        Mono<String> requestMono = serverRequest.bodyToMono(String.class);
        Mono<String> workflowResponseMono = requestMono.flatMap(body -> workflowManager.executeWorkflow(path, body));
        return ServerResponse.ok()
                .body(workflowResponseMono, new ParameterizedTypeReference<>() {
                });
    }

}
