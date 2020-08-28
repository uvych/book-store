package com.geekbrains.book.store.soap;


import com.geekbrains.book.store.entities.Book;
import com.some.ws.bookstore.Genre;
import com.some.ws.bookstore.GetBooksResponse;
import com.some.ws.bookstore.SoapBook;


import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static GetBooksResponse mapBook(List<Book> books) {
        GetBooksResponse booksResponse = new GetBooksResponse();
        booksResponse.getBook().addAll(getListResponseBooks(books));
        return booksResponse;
    }

    private static List<SoapBook> getListResponseBooks(List<Book> books) {
        List<SoapBook> responseBooks = new ArrayList<>();

        for (Book book : books) {
            SoapBook soapBook = new SoapBook();
            soapBook.setDescription(book.getDescription());
            soapBook.setId(book.getId());
            soapBook.setPrice(book.getPrice().intValue());
            soapBook.setPublishYear(book.getPublishYear());
            soapBook.setTitle(book.getTitle());
            soapBook.setGenre(Genre.fromValue(book.getGenre().toString()));
            responseBooks.add(soapBook);
        }
        return responseBooks;
    }

}
