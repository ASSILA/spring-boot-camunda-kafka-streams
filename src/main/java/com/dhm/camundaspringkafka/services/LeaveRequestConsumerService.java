package com.dhm.camundaspringkafka.services;

import com.dhm.camundaspringkafka.ProcessConstants;
import com.dhm.camundaspringkafka.ValidationResult;
import com.dhm.camundaspringkafka.entities.LeaveRequest;
import org.camunda.bpm.engine.ProcessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class LeaveRequestConsumerService {
    private final Logger log = LoggerFactory.getLogger(LeaveRequestConsumerService.class);

    @Autowired
    private ProcessEngine processEngine;

    @Bean
    public Consumer<LeaveRequest> leaveRequestConsumer(){
        return (input)->{
            handleLeaveRequestValidationEvent(input.getLeaveReqId());
        };
    }
    public void handleLeaveRequestValidationEvent(String leaveReqId) {
        processEngine.getRuntimeService().createMessageCorrelation(ProcessConstants.MSG_NAME_leaveRequest_Validated) //
                .processInstanceVariableEquals(ProcessConstants.VAR_NAME_leaveReqId, leaveReqId) //
                .setVariable(ProcessConstants.VARIABLE_validation,Math.random()>0.5? ValidationResult.APPROUVED.toString(): ValidationResult.NOT_APPRPOUVED.toString()) //
                .correlateWithResult();
    }
}
