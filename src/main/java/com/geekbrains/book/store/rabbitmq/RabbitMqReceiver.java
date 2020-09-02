package com.geekbrains.book.store.rabbitmq;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.services.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceiver {
    private RabbitTemplate rabbitTemplate;

    private static final String ORDER_READY="Order ready:";

    @Autowired
    private OrderService orderService;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void receiveMessage(byte[] message) {
        String messageText = new String(message);
        System.out.println("Received: <" + messageText + ">");
        int res = checkMessage(messageText);
        try {
            orderService.updateOrderStatus((long) res, Order.Status.IS_DONE);
        }catch (ResourceNotFoundException e){
            System.out.println("Order with id: " + res + " not found");
        }

    }
    public int checkMessage(String message){
            try {
                return Integer.parseInt(message.substring(message.indexOf(ORDER_READY)+ORDER_READY.length()));
            }catch (Exception e) {
                throw new RuntimeException("Illegal Command!");
            }
    }
}