package com.example.minor1.repository;

import com.example.minor1.model.Book;
import com.example.minor1.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByName(String value);

    List<Book> findByAuthorName(String value);

    List<Book> findByGenre(Genre value);
}
