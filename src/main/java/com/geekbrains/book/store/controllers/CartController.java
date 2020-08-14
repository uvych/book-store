package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping("/cart")
    public String showCart(Model model){
        model.addAttribute("order", cart);
        return "cart-page";
    }


}
