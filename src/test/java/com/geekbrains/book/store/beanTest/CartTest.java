package com.geekbrains.book.store.beanTest;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartTest {

    @Autowired
    Cart cart;

    Book book;

    @BeforeEach
    public void init() {
        book = new Book();
        book.setId(2L);
        book.setDescription("description");
        book.setGenre(Book.Genre.DETECTIVE);
        book.setPrice(BigDecimal.valueOf(400));
        book.setPublishYear(1234);
        book.setTitle("Title");
    }

    @Test
    void methodAddTest() {
        cart.add(book);
        assertEquals(cart.getItems().get(0).getBook(), book);
    }

    @Test
    void methodAddRecalculateTest() {
        cart.add(book);
        cart.add(book);
        assertEquals(1, cart.getItems().size());
        assertEquals(new BigDecimal(800), cart.getPrice());
    }

    @Test
    void methodDecrementTest() {
        cart.add(book);
        cart.add(book);
        cart.add(book);
        cart.decrement(book);
        assertEquals(1, cart.getItems().size());
        assertEquals(new BigDecimal(800), cart.getPrice());
    }

    @Test
    void methodDecrementEmptyItemsTest() {
        cart.add(book);
        cart.decrement(book);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    void methodRemoveByIdTest() {
        cart.add(book);
        cart.removeByProductId(2L);
        assertEquals(0, cart.getItems().size());
    }
}
