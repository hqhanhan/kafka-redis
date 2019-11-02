package com.hqhan.school;

import com.hqhan.school.mq.core.MessageVerifier;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class KafkaTest extends BaseCaseTest {

    @Autowired
    private MessageVerifier messageVerifier;

    @Test
    public void splitRequest() {

    }
}
