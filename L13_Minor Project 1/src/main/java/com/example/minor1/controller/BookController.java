package com.example.minor1.controller;

import com.example.minor1.model.Book;
import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.request.BookFilterType;
import com.example.minor1.response.BookSearchResponse;
import com.example.minor1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody BookCreateRequest bookCreateRequest){
        bookService.create(bookCreateRequest);
    }

    // search for book in library.
    // search with Genre.
    // search with Author.

    // localhost:9000/books/search?filter=GENRE&value=HISTORY

    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(@RequestParam("filter") BookFilterType bookFilterType,
                                              @RequestParam("value") String value){
        List<Book> bookList = bookService.find(bookFilterType, value);

                return bookList.stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }


}
