package com.geekbrains.book.store.services;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final Cart cart;

    public void saveOrUpdate(User user) {
        Map<Book, Integer> allOrders = cart.getBooksCart();
        Order order = new Order();
        List<OrderItem> orderItemList=new ArrayList<>();

        for(Map.Entry<Book,Integer> orderBook:allOrders.entrySet()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(orderBook.getKey());
            orderItem.setCount(orderBook.getValue());
            orderItem.setPrice(orderBook.getKey().getPrice().intValue()*orderBook.getValue());

            orderItemList.add(orderItem);
        }
        order.setOrderItems(orderItemList);
        order.setUser(user);
        orderRepository.save(order);
    }
}
