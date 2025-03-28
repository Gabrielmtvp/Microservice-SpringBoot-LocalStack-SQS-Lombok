package com.rocket.sqsmicroservice.checkout.producer.sqs;

import com.rocket.sqsmicroservice.checkout.producer.model.CheckoutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.Map;

import static com.rocket.sqsmicroservice.checkout.producer.utils.Json.toJson;

@Component
public class SqsPublisher {

    private final SqsAsyncClient sqsAsyncClient;
    private static final Logger log = LoggerFactory.getLogger(SqsPublisher.class);

    public SqsPublisher(SqsAsyncClient sqsAsyncClient) {
        this.sqsAsyncClient = sqsAsyncClient;
    }

    public <T>Mono<SendMessageResponse> sendToConvertJsonStatementSqs(final T object, final Map<String, MessageAttributeValue> messageAttributes, final String queue) {
        final String messageBody = toJson(object);
        return sendSqsQueue(messageBody, messageAttributes, queue);
    }

    public Mono<SendMessageResponse> sendSqsQueue(final String message, final Map<String, MessageAttributeValue> messageAttributes, final String queue) {
        log.info("Sending message to SQS queue: {}", queue);
        log.debug("Message body: {}", message);

        final SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queue)
                .messageAttributes(messageAttributes)
                .messageBody(message)
                .build();

        return Mono.fromFuture(sqsAsyncClient.sendMessage(request))
                .doOnSuccess(result -> log.info("Message sent successfully to queue: {}, MessageId: {}", queue, result.messageId()))
                .doOnError(error -> log.error("Failed to send message to queue: {}, Error: {}", queue, error.getMessage(), error));
    }
}
