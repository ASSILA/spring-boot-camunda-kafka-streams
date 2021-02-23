package com.dhm.camundaspringkafka.adapter;

import com.dhm.camundaspringkafka.ProcessConstants;
import com.dhm.camundaspringkafka.ValidationResult;
import com.dhm.camundaspringkafka.entities.LeaveRequest;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class SendLeaveRequestAdapter implements JavaDelegate {

    private final Logger log = LoggerFactory.getLogger(SendLeaveRequestAdapter.class);

    @Autowired
    private StreamBridge streamBridge;

    private String topic ="LR1";
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String leaveReqKey = (String) delegateExecution.getVariable(ProcessConstants.PROCESS_KEY_leave);
        String startDate = (String) delegateExecution.getVariable(ProcessConstants.VAR_NAME_startDate);
        String absenceType = (String) delegateExecution.getVariable(ProcessConstants.VAR_NAME_absenceType);
        String endDate = (String) delegateExecution.getVariable(ProcessConstants.VAR_NAME_endDate);
        String userId = (String) delegateExecution.getVariable(ProcessConstants.VAR_NAME_userId);
        String leaveReqId = (String) delegateExecution.getVariable(ProcessConstants.VAR_NAME_leaveReqId);
        String managerId = (String) delegateExecution.getVariable(ProcessConstants.VARIABLE_managerId);
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setLeaveReqId(leaveReqId);
        leaveRequest.setLeaveReqKey(leaveReqKey);
        leaveRequest.setUserId(userId);
        leaveRequest.setAbsenceType(absenceType);
        leaveRequest.setManagerId(managerId);
        leaveRequest.setStartDate(startDate);
        leaveRequest.setEndDate(endDate);
        leaveRequest.setValidation(ValidationResult.APPROUVED.toString());
        // Kafka
        streamBridge.send(topic,leaveRequest);
    }
}
