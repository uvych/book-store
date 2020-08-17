package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final UserService userService;

    private final OrderService orderService;

    @GetMapping("/saveOrder")
    public String  saveAllOrders(Principal principal){
        User user = userService.findByUsername(principal.getName());
        orderService.saveOrUpdate(user);
        return "redirect:/books";
    }
}
