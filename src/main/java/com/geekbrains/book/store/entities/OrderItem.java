package com.geekbrains.book.store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderItem {
    private Long id;
    private Book book;
    private Integer count;
    private Long price;
    private User user;
}
