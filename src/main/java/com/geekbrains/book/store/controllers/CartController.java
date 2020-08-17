package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.OrderItemService;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final Cart cart;

    private final UserService userService;

    private final BookService bookService;




    @GetMapping
    public String showAllOrders(Model model){
        Map<Book, Integer> allOrder = cart.getBooksCart();
        model.addAttribute("allOrder", allOrder.entrySet());
        return "cart-page";
    }

    @GetMapping("/addToCart/{id}")
    public String addBookToCart(@PathVariable(required = false) Long id){
        cart.addBookToCart(bookService.findById(id));
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id){
        Book book = bookService.findById(id);
        cart.deleteBook(book);
        return "redirect:/cart";
    }
}
