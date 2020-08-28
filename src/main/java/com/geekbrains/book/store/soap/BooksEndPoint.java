package com.geekbrains.book.store.soap;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.some.ws.bookstore.GetBooksRequest;
import com.some.ws.bookstore.GetBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
public class BooksEndPoint {

    private static final String NAMESPACE_URI="http://some.com/ws/bookstore";

    private final BookService bookService;

    @Autowired
    public BooksEndPoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getBooks(@RequestPayload GetBooksRequest request){
        List<Book> curBooks = bookService.findAll();
        return Mapper.mapBook(curBooks);
    }
}
