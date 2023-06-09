package com.lottomatica.lia.ares.workflow;

import com.lottomatica.lia.ares.properties.WorkflowConfiguration;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkflowManagerTest {

    @InjectMocks
    WorkflowManager workflowManager;

    @Mock
    WorkflowConfiguration workflowConfiguration;

    @Mock
    StepExecutor executor;

    @Test
    void executeWorkflow() {
        String workflowPath = "/payout";
        String body = Instancio.create(String.class);
        IStepExecutor.StepResponse stepResponse = Instancio.create(IStepExecutor.StepResponse.class);
        Map<String, WorkflowConfiguration.Workflow> workflowMap = new HashMap<>(){{
            put("payout", Instancio.create(WorkflowConfiguration.Workflow.class));
        }};
        Mockito.when(workflowConfiguration.getWorkflows()).thenReturn(workflowMap);

        when(executor.executeStep(any(), any()))
                .thenReturn(Mono.just(stepResponse))
                .thenReturn(Mono.empty());
        Mono<String> monoToTest = workflowManager.executeWorkflow(workflowPath, body);
        StepVerifier.create(monoToTest)
                .expectSubscription()
                .expectNext(stepResponse.body())
                .expectComplete()
                .verify();
    }
}
