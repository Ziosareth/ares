package com.lottomatica.lia.ares.endpoint;

import com.lottomatica.lia.ares.handler.IAresHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@Slf4j
@RequiredArgsConstructor
public class AresEndpoint {
    @Bean
    RouterFunction<ServerResponse> aresRoute(IAresHandler aresHandler){
        return route(POST("/**"), aresHandler::workflowHandler);
    }

}
