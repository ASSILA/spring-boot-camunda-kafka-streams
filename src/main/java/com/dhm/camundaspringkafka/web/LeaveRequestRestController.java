package com.dhm.camundaspringkafka.web;

import com.dhm.camundaspringkafka.ProcessConstants;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/leave")
public class LeaveRequestRestController {

    private final Logger log = LoggerFactory.getLogger(LeaveRequestRestController.class);
    @Autowired
    private ProcessEngine engine;

    @PostMapping("/request")
    public void requestAbsencePost(@RequestParam String leaveReqId, @RequestParam String userId, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String absenceType) {
        placeLeaveRequest(leaveReqId, userId, startDate, endDate, absenceType);
    }

    public ProcessInstance placeLeaveRequest(String leaveReqId, String userId, String startDate, String endDate, String absenceType) {
        return engine.getRuntimeService().startProcessInstanceByKey(
                ProcessConstants.PROCESS_KEY_leave,
                Variables
                        .putValue(ProcessConstants.VAR_NAME_leaveReqId, leaveReqId)
                        .putValue(ProcessConstants.VAR_NAME_userId, userId)
                        .putValue(ProcessConstants.VAR_NAME_startDate, startDate)
                        .putValue(ProcessConstants.VAR_NAME_endDate, endDate)
                        .putValue(ProcessConstants.VAR_NAME_absenceType, absenceType)

        );
    }

}
