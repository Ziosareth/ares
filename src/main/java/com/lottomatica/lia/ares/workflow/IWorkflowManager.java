package com.lottomatica.lia.ares.workflow;

import reactor.core.publisher.Mono;

public interface IWorkflowManager {

    Mono<String> executeWorkflow(String workflowName, String body);

}
