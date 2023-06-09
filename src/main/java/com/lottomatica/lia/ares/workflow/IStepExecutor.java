package com.lottomatica.lia.ares.workflow;

import com.lottomatica.lia.ares.properties.WorkflowConfiguration;
import reactor.core.publisher.Mono;

public interface IStepExecutor {

    record StepResponse(String body, String nextStep){};
    Mono<StepResponse> executeStep(WorkflowConfiguration.Step step, String body);

}
