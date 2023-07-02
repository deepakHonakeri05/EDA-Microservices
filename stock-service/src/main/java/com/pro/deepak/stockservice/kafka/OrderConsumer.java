package com.pro.deepak.stockservice.kafka;

import com.pro.deepak.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);


    @KafkaListener(topics = "order_service", groupId = "stock")
    public void consume(OrderEvent event){
        LOGGER.info("Event rcvd -> " + event.toString());

        //save the order event data to database
    }
}
