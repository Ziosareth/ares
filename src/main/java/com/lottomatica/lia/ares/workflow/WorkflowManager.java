package com.lottomatica.lia.ares.workflow;

import com.lottomatica.lia.ares.properties.WorkflowConfiguration;
import com.lottomatica.lia.ares.workflow.IStepExecutor.StepResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkflowManager implements IWorkflowManager {

    private final WorkflowConfiguration workflowConfiguration;
    private final IStepExecutor stepExecutor;

    @Override
    public Mono<String> executeWorkflow(String workflowPath, String body) {
        String workflowName = workflowPath.replaceAll("/", "");
        WorkflowConfiguration.Workflow workflow = workflowConfiguration.getWorkflows().get(workflowName);
        //esegue step iniziale
        String initStep = workflow.getInitStep();

        return stepExecutor.executeStep(workflow.getSteps().get(initStep), body)
                .expand(step -> stepExecutor.executeStep(workflow.getSteps().get(step.nextStep()), step.body()))
                .map(StepResponse::body).last();
    }
}
