package com.storefront.controller;


import com.storefront.dto.Order;
import com.storefront.service.OrderService;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderConfirmation(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<String> submitCheckoutForm(@RequestBody Order order) throws ValidationException {
        return ResponseEntity.ok(orderService.save(order));
    }
}

