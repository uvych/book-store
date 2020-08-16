package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.repositories.BookRepository;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAll(Specification<Book> spec, int page, int size) {
        return bookRepository.findAll(spec, PageRequest.of(page, size));
    }

    public String getParamString(MultiValueMap<String, String> mapParam){
        StringBuilder paramString = new StringBuilder();

        if (mapParam.containsKey("maxPrice") && !mapParam.get("maxPrice").get(0).isEmpty()){
            paramString.append("&maxPrice=").append(mapParam.get("maxPrice").get(0));
        }

        if (mapParam.containsKey("minPrice") && !mapParam.get("minPrice").get(0).isEmpty()){
            paramString.append("&minPrice=").append(mapParam.get("minPrice").get(0));
        }

        if (mapParam.containsKey("titlePrice") && !mapParam.get("titlePart").get(0).isEmpty()){
            paramString.append("&titlePart=").append(mapParam.get("titlePart").get(0));
        }

        if (mapParam.containsKey("genre")) {
            for(String param : mapParam.get("genre")) {
                paramString.append("&genre=").append(param);
            }
        }
        return paramString.toString();
    }
}
