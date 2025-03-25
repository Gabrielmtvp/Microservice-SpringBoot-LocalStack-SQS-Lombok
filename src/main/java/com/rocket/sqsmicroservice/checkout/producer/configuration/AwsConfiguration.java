package com.rocket.sqsmicroservice.checkout.producer.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AwsConfiguration {
    @Value(value = "${aws.region}")
    private String region;

    @Value(value = "${aws.access-key}")
    private String accessKey;

    @Value(value = "${aws.secret-key}")
    private String secretKey;

    @Value(value = "${aws.sqs.process-order-queue}")
    private String processOrderQueueUrl;
}
