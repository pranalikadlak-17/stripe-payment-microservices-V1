package com.example.payment_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
@RequestMapping("/webhook")
@RestController
public class WebhookController {
	@Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping
    public String handleWebhook(@RequestBody String payload,
                                @RequestHeader("Stripe-Signature") String sigHeader) {

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            return "Invalid signature";
        }

        // Handle event
        System.out.println("Webhook received: " + event.getType());
        if ("checkout.session.completed".equals(event.getType())) {
            System.out.println("✅ Payment Successful");
        } else {
            System.out.println("Unhandled event: " + event.getType());
        }

        return "success";

}
    }
