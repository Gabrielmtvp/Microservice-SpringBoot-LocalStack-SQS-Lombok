package com.rocket.sqsmicroservice.checkout.producer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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

    @Value("${aws.sqs.inventoryUpdateQueueUrl}")
    private String inventoryUpdateQueueUrl;

    @Value("${aws.sqs.paymentProcessingQueueUrl}")
    private String paymentProcessingQueueUrl;

    @Value("${aws.sqs.shippingNotificationQueueUrl}")
    private String shippingNotificationQueueUrl;

    public String getRegion() {
        return region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getProcessOrderQueueUrl() {
        return processOrderQueueUrl;
    }

    public String getInventoryUpdateQueueUrl() {
        return inventoryUpdateQueueUrl;
    }

    public String getPaymentProcessingQueueUrl() {
        return paymentProcessingQueueUrl;
    }

    public String getShippingNotificationQueueUrl() {
        return shippingNotificationQueueUrl;
    }
}
