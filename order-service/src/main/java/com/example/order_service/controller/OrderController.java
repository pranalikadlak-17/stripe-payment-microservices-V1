package com.example.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.service.OrderService;
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }
	

}
