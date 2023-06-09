package com.lottomatica.lia.ares.workflow;

import com.lottomatica.lia.ares.properties.WorkflowConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class StepExecutor implements IStepExecutor{

    private final IAresGateway aresGateway;

    @Override
    public Mono<StepResponse> executeStep(WorkflowConfiguration.Step step, String body) {
        if (step == null){
            return Mono.empty();
        }
        return aresGateway.exchange(step.getMethod(), step.getEndpoint(), body)
                .map(exchangeResponse-> new StepResponse(exchangeResponse, step.getNext()));
    }
}
