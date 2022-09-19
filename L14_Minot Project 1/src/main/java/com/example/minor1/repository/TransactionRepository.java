package com.example.minor1.repository;

import com.example.minor1.model.Book;
import com.example.minor1.model.Student;
import com.example.minor1.model.Transaction;
import com.example.minor1.request.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Query : select * from Transaction where studentId = ? and bookId = ? and transactionType = 'ISSUE' order by txn_date desc limit 1;

    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType transactionType);


}
