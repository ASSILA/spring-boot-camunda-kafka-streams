package com.dhm.camundaspringkafka.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveRequest {
    private String leaveReqKey;
    private String leaveReqId;
    private String userId;
    private String startDate;
    private String endDate;
    private String absenceType;
    private String managerId;
    private String validation;
}
