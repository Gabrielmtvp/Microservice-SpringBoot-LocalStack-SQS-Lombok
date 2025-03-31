package com.rocket.sqsmicroservice.checkout.producer.service;

import com.rocket.sqsmicroservice.checkout.producer.configuration.AwsConfiguration;
import com.rocket.sqsmicroservice.checkout.producer.model.CheckoutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.rocket.sqsmicroservice.checkout.producer.sqs.SqsPublisher;

import static java.util.Collections.emptyMap;

@Service
public class CheckoutService {
    private final AwsConfiguration awsConfiguration;
    private final SqsPublisher sqsPublisher;
    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    public CheckoutService(AwsConfiguration awsConfiguration, SqsPublisher sqsPublisher, OrderService orderService) {
        this.awsConfiguration = awsConfiguration;
        this.sqsPublisher = sqsPublisher;
        this.orderService = orderService;
    }

    public Mono<Void> sendToProcessOrderQueue(CheckoutMessage checkoutMessage) {
        log.info("Preparing to send message to SQS: {}", checkoutMessage);

        return sqsPublisher.sendToConvertJsonStatementSqs(
                checkoutMessage, emptyMap(), awsConfiguration.getProcessOrderQueueUrl()
        ).then();
    }

    public Mono<Void> sendToInventoryUpdateQueue(CheckoutMessage checkoutMessage) {
        log.info("Preparing to send inventory update message to SQS: {}", checkoutMessage);

        return sqsPublisher.sendToConvertJsonStatementSqs(
                checkoutMessage, emptyMap(), awsConfiguration.getInventoryUpdateQueueUrl()
        ).then();
    }

    public Mono<Void> sendToPaymentProcessingQueue(CheckoutMessage checkoutMessage) {
        log.info("Preparing to send payment processing message to SQS: {}", checkoutMessage);

        return sqsPublisher.sendToConvertJsonStatementSqs(
                checkoutMessage, emptyMap(), awsConfiguration.getPaymentProcessingQueueUrl()
        ).then();
    }

    public Mono<Void> sendToShippingNotificationQueue(CheckoutMessage checkoutMessage) {
        log.info("Preparing to send shipping notification message to SQS: {}", checkoutMessage);

        return sqsPublisher.sendToConvertJsonStatementSqs(
                checkoutMessage, emptyMap(), awsConfiguration.getShippingNotificationQueueUrl()
        ).then();
    }
}
