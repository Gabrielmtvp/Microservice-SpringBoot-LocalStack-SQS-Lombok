package com.rocket.sqsmicroservice.checkout.producer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Json {
    private static final ObjectMapper objectMapper;

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
