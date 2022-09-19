package com.example.minor1.service;

import com.example.minor1.model.Author;
import com.example.minor1.model.Book;
import com.example.minor1.model.Genre;
import com.example.minor1.repository.AuthorRepository;
import com.example.minor1.repository.BookRepository;
import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.request.BookFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public void create(BookCreateRequest bookCreateRequest) {

        Book book = bookCreateRequest.to();

        Author author = book.getAuthor();

        // Find if author with given email id present in DB or not.
        // if present we will directly store book object.
        // otherwise we will first store author and then store book object.

//        Author authorFromDB = authorRepository.getAuthorByGivenEmailIdNative2(author.getEmail());
        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        if(authorFromDB == null){
            authorFromDB = authorRepository.save(author);
        }

        book.setAuthor(authorFromDB);
//        Author author = book.getAuthor();
//        author.setId(1);

        bookRepository.save(book);
    }

    public List<Book> find(BookFilterType bookFilterType, String value) {
        switch (bookFilterType){
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthorName(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            default:
                return new ArrayList<>();
        }

    }
}
