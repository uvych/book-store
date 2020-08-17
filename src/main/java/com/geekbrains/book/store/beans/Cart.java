package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private Map<Book,Integer> booksCart;

    @PostConstruct
    public void initCart() {
        booksCart = new HashMap<>();
    }

    public void addBookToCart(Book book){
        if(booksCart.containsKey(book)){
            booksCart.put(book, booksCart.get(book) + 1);
        }else {
            booksCart.put(book, 1);
        }
    }

    public void deleteBook(Book book){
        if(booksCart.containsKey(book)){
            if(booksCart.get(book) == 1) {
                booksCart.remove(book);
            } else {
                booksCart.put(book, booksCart.get(book) - 1);
            }
        }
    }
}
