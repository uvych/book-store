package com.geekbrains.book.store.controllersTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerTest {

    private static final String url = "/api/v1/books/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getAllBooksTest() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setId(1L);
        book.setDescription("description");
        book.setGenre(Book.Genre.DETECTIVE);
        book.setPrice(BigDecimal.valueOf(400));
        book.setPublishYear(1234);
        book.setTitle("Title");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setDescription("description");
        book2.setGenre(Book.Genre.DETECTIVE);
        book2.setPrice(BigDecimal.valueOf(400));
        book2.setPublishYear(1234);
        book2.setTitle("Title");

        books.add(book);
        books.add(book2);

        given(bookService.findAll()).willReturn(books);

              mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setDescription("description");
        book.setGenre(Book.Genre.DETECTIVE);
        book.setPrice(BigDecimal.valueOf(400));
        book.setPublishYear(1234);
        book.setTitle("Title");

        given(bookService.findById(1L))
                .willReturn(book);

        mockMvc.perform(get(url + "{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void createNewBookTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setDescription("description");
        book.setGenre(Book.Genre.DETECTIVE);
        book.setPrice(BigDecimal.valueOf(400));
        book.setPublishYear(1234);
        book.setTitle("Title");

        given(this.bookService.saveOrUpdate(Mockito.any())).willReturn(book);

        mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void modifyBookTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setDescription("description");
        book.setGenre(Book.Genre.DETECTIVE);
        book.setPrice(BigDecimal.valueOf(400));
        book.setPublishYear(1234);
        book.setTitle("Title");

        given(bookService.saveOrUpdate(Mockito.any())).willReturn(book);
        given(bookService.existsById(book.getId())).willReturn(true);

        mockMvc.perform(put(url)
                .content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }
}
