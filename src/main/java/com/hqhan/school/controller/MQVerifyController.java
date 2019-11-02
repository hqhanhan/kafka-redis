package com.hqhan.school.controller;


import com.hqhan.school.mq.Message;
import com.hqhan.school.mq.core.MessageVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MQVerifyController {

    @Autowired
    private MessageVerifier messageVerifier;

    @PostMapping(value = "/verifyBeforeConsumption", consumes = "application/json;charset=UTF-8")
    public boolean verifyBeforeConsumption(@RequestBody Message message) {
        Message msg = messageVerifier.verifyBeforeConsumption(message);
        return msg == null ? false : true;
    }


    @PostMapping("/verifyAfterConsumption")
    public boolean verifyAfterConsumption(@RequestBody Message message) {
        return messageVerifier.verifyAfterConsumption(false, message);
    }

}