package com.example.demojpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {

    @Id
    private Integer id;

    private String authorName;

    private String address;
}
