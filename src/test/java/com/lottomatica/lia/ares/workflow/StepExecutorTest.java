package com.lottomatica.lia.ares.workflow;

import com.lottomatica.lia.ares.properties.WorkflowConfiguration;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StepExecutorTest {
    @InjectMocks
    StepExecutor stepExecutor;
    @Mock
    IAresGateway iAresGateway;
    @Test
    @DisplayName("Step executor runs a step")
    void executeStep() {
        WorkflowConfiguration.Step step = Instancio.create(WorkflowConfiguration.Step.class);
        String gatewayResponse = Instancio.create(String.class);
        String body = Instancio.create(String.class);
        IStepExecutor.StepResponse stepResponse= new IStepExecutor.StepResponse(gatewayResponse,step.getNext());
        when(iAresGateway.exchange(step.getMethod(), step.getEndpoint(), body)).thenReturn(Mono.just(gatewayResponse));
        Mono<IStepExecutor.StepResponse> toTest = stepExecutor.executeStep(step, body);

        StepVerifier.create(toTest).expectSubscription()
                .expectNext(stepResponse)
                .expectComplete().verify();
    }

    @Test
    @DisplayName("Step executor has done all steps")
    void executeStepDone() {
        WorkflowConfiguration.Step step = Instancio.create(WorkflowConfiguration.Step.class);
        String body = Instancio.create(String.class);
        when(iAresGateway.exchange(step.getMethod(), step.getEndpoint(), body)).thenReturn(Mono.empty());
        Mono<IStepExecutor.StepResponse> toTest = stepExecutor.executeStep(step, body);

        StepVerifier.create(toTest).expectSubscription()
                .expectComplete()
                .verify();
    }
}