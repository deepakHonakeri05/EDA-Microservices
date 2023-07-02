package com.pro.deepak.orderservice.controller;

import com.pro.deepak.basedomains.dto.Order;
import com.pro.deepak.basedomains.dto.OrderEvent;
import com.pro.deepak.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderConrtoller {

    private OrderProducer orderProducer;

    public OrderConrtoller(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId((UUID.randomUUID().toString()));
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setOrder(order);
        orderEvent.setMessage("Order status is in pending state");

        orderProducer.sendMessage(orderEvent);

        return "Order Placed Successfully";
    }
}
