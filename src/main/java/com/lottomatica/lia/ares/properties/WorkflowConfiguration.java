package com.lottomatica.lia.ares.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "ares")
@Data
public class WorkflowConfiguration {
    private Map<String, Workflow> workflows;

    @Data
    public static class Workflow {
        private String initStep;
        private Map<String, Step> steps;
        private End end;
    }

    @Data
    public static class Step {
        private String endpoint;
        private String method;
        private String requestBody;
        private String next;
        private String error;
    }

    @Data
    public static class End {
        String returnValue;
    }
}
