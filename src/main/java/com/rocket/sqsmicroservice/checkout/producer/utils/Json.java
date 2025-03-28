package com.rocket.sqsmicroservice.checkout.producer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocket.sqsmicroservice.checkout.producer.controller.CheckoutController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Json {
    private static final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(Json.class);

    static {
        objectMapper = new ObjectMapper();
    }

    public static String toJson (Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("error ocurred");
            throw new RuntimeException(e);
        }
    }
}
