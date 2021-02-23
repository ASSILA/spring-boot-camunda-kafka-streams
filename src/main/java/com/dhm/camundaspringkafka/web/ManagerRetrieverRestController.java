package com.dhm.camundaspringkafka.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
public class ManagerRetrieverRestController {

    public static class CreateGetManagerByUserIdRequest {
        public String userId;
    }

    @RequestMapping(path="/getManagerByUserId", method= RequestMethod.POST)
    public String createCharge(@RequestBody CreateGetManagerByUserIdRequest request) {
        return "{\"managerId\": \"061988\"}";
    }

}
