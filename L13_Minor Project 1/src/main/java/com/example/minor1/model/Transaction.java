package com.example.minor1.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date transactionDate;

    private double payment;

    @ManyToOne
    @JoinColumn
    private Book my_book;

    @ManyToOne
    @JoinColumn
    private Student my_student;



}
