package com.example.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.order_service.dto.OrderRequest;
@Service
public class OrderService {
	@Autowired
	private RestTemplate restTemplate; 
	public String createOrder(OrderRequest request) {
		System.out.println("Calling payment service for order: " + request.getProductName() + " with amount: " + request.getAmount());
		//payment service api
		 String paymentUrl = "http://localhost:8081/payments/create";
		 String paymentResponse = restTemplate.postForObject(
				 paymentUrl, 
				 request,
				 String.class
				 );
        return "Order Created for " + request.getProductName() +
        		" with amount " + request.getAmount() + ". Payment Status: " + paymentResponse;

}
	}
