package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private String filterParams;
    private final List<Specification<Book>> bookSpecifications = new ArrayList<>();

    public BookFilter(Map<String, String> params) {
        spec = Specification.where(null);
        if (params.containsKey("maxPrice") && !params.get("maxPrice").isEmpty()) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.get("maxPrice"))));
        }
        if (params.containsKey("minPrice") && !params.get("minPrice").isEmpty()) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("minPrice"))));
        }
        if (params.containsKey("titlePart")) {
            assert spec != null;
            spec = spec.and(BookSpecifications.titleLike(params.get("titlePart")));
        }
        if (params.containsKey("genre")) {
            bookSpecifications.add(BookSpecifications.genreEqualsThan(Enum.valueOf(Genre.class, params.get("genre"))));
        }
        if (params.containsKey("genre2")) {
            bookSpecifications.add(BookSpecifications.genreEqualsThan(Enum.valueOf(Genre.class, params.get("genre2"))));
        }
        if (params.containsKey("genre3")) {
            bookSpecifications.add(BookSpecifications.genreEqualsThan(Enum.valueOf(Genre.class, params.get("genre3"))));
        }

        if (bookSpecifications.size() != 0){
            Specification<Book> specification = creat(bookSpecifications);
            spec = spec.and(specification);
        }
    }


    public Specification<Book> creat (List<Specification<Book>> listSpec) {
        if (listSpec.size() == 1) {
            return listSpec.get(0);
        }

        if (listSpec.size() == 2){
            return listSpec.get(0).or(listSpec.get(1));
        }

        if (listSpec.size() == 3) {
            return Objects.requireNonNull(Objects.requireNonNull(listSpec.get(0).or(listSpec.get(1))).or(listSpec.get(2)));
        }

        return null;
    }
}
