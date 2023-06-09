package com.lottomatica.lia.ares;

import com.lottomatica.lia.ares.properties.WorkflowConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(WorkflowConfiguration.class)
public class AresApplication {

    public static void main(String[] args) {
        SpringApplication.run(AresApplication.class, args);
    }


}
