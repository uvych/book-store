package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping("/create")
    public String createOrder(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName()).get();
        model.addAttribute("user", user);
        return "order_info";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        Order order = new Order(user, cart);
        order.setStatus(Order.Status.IN_PROCESS);
        order = orderService.saveOrder(order);
        rabbitMqService.sendMessage( order.getId().intValue());
        return order.getId() + " " + order.getPrice();
    }
}
