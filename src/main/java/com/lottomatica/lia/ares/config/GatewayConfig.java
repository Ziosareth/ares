package com.lottomatica.lia.ares.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class GatewayConfig {

    @Bean
    WebClient webClient(){
        return WebClient.create();
    }

}
