package com.dhm.camundaspringkafka.adapter;

import com.dhm.camundaspringkafka.ProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RetrieveManagerAdapter implements JavaDelegate {
    @Autowired
    private RestTemplate restTemplate;

    public static class CreateGetManagerByUserIdResponse {
        public String managerId;
    }

    public static class CreateGetManagerByUserIdRequest {
        public String userId;
    }

    public String restEndpoint() {
        return "http://localhost:8080/api/manager/getManagerByUserId";
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CreateGetManagerByUserIdRequest request = new CreateGetManagerByUserIdRequest();

        request.userId = (String) delegateExecution.getVariable(ProcessConstants.VAR_NAME_userId);

        CreateGetManagerByUserIdResponse response = restTemplate.postForObject(restEndpoint(), request, CreateGetManagerByUserIdResponse.class);

        delegateExecution.setVariable(ProcessConstants.VARIABLE_managerId, response.managerId);
    }
}

