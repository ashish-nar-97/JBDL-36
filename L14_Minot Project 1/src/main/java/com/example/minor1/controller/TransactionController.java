package com.example.minor1.controller;

import com.example.minor1.exception.TxnServiceException;
import com.example.minor1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction/issue")
    public void issueTxn(@RequestParam("bookId") Integer bookId, @RequestParam("studentId") Integer studentId) throws TxnServiceException {
        transactionService.issueTransaction(bookId, studentId);
    }

    @PostMapping("/transaction/return")
    public void returnTxn(@RequestParam("bookId") Integer bookId, @RequestParam("studentId") Integer studentId) throws TxnServiceException {
        transactionService.returnTransaction(bookId, studentId);
    }

    // SID = 1, Name : Ashish
    // SID = 2, Name : Rajesh

    // localhost:9000/transaction/return?bookId=1&studentId=1

    // localhost:9000/transaction/return?bookId=2&studentId=2

    // Spring Security Layer.
    
    // Ashish Logging In : SID = 1
}
