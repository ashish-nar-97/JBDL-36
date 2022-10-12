package com.example.minor1.response;

import com.example.minor1.model.Author;
import com.example.minor1.model.Genre;
import com.example.minor1.model.Student;
import com.example.minor1.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchResponse {

    private int id;
    private String name;
    private int cost;
    private Genre genre;

    private Student student;

    private List<Transaction> transactionList;

    @JsonIgnoreProperties({"bookList", "createdOn"})
    private Author author;

    private Date createdOn;

    private Date updatedOn;
}
