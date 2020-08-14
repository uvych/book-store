package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    public void addBook(Book book){

    }

    public void deleteAllBook(){}
}
