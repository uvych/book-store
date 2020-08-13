package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private final List<Specification<Book>> bookSpecifications = new ArrayList<>();

    public BookFilter(MultiValueMap<String, String> params) {
        spec = Specification.where(null);
        if (params.containsKey("maxPrice") && !params.get("maxPrice").get(0).isEmpty()) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.get("maxPrice").get(0))));
        }
        if (params.containsKey("minPrice") && !params.get("minPrice").get(0).isEmpty()) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("minPrice").get(0))));
        }
        if (params.containsKey("titlePart") && !params.get("titlePart").get(0).isEmpty()) {
            assert spec != null;
            spec = spec.and(BookSpecifications.titleLike(params.get("titlePart").get(0)));
        }
        if (params.containsKey("genre")) {
            for(String param : params.get("genre")) {
                bookSpecifications.add(BookSpecifications.genreEqualsThan(Enum.valueOf(Genre.class, param)));
            }
        }

        if (bookSpecifications.size() != 0){
            Specification<Book> specification = creatOrSpecification(bookSpecifications);
            spec = spec.and(specification);
        }
    }


    public Specification<Book> creatOrSpecification (List<Specification<Book>> listSpec) {
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
