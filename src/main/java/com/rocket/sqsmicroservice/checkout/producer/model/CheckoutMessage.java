package com.rocket.sqsmicroservice.checkout.producer.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class CheckoutMessage {
    private String orderId;
    private String sellerId;
    private long amount;
    private String currency;
    private String timestamp;
    private String customerName;
    private String customerEmail;
}
