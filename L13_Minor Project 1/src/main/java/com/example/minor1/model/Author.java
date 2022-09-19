package com.example.minor1.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "land")
    private String country;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> bookList;

    @CreationTimestamp
    private Date createdOn;


}
