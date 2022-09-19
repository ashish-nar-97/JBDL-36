package com.example.minor1.service;

import com.example.minor1.exception.TxnServiceException;
import com.example.minor1.model.Book;
import com.example.minor1.model.Student;
import com.example.minor1.model.Transaction;
import com.example.minor1.repository.TransactionRepository;
import com.example.minor1.request.BookFilterType;
import com.example.minor1.request.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${book.return.due-date}")
    int number_of_days;

    public String issueTransaction(Integer bookId, Integer studentId) throws TxnServiceException {

        /**
         *  1] check if student is valid entity.
         * 2] check if book is present and available.
         * 3] create issue transaction ==> saving transaction into Transaction table.
         * 4] Make a book unavailable.
         * */

        Student student = studentService.findStudentByStudentId(studentId);
        if (student == null) {
            throw new TxnServiceException("Student is Not Present.");
        }
        List<Book> bookList = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));

        if (bookList == null || bookList.size() != 1 || bookList.get(0).getStudent() != null) {
            throw new TxnServiceException("Book is Not Present.");
        }
        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(bookList.get(0).getCost())
                .book(bookList.get(0))
                .student(student)
                .build();

        transactionRepository.save(transaction);
        bookList.get(0).setStudent(student);

        // save() ==> if record already present then update else create.
        bookService.create(bookList.get(0));

        return transaction.getExternalTxnId();

    }

    public String returnTransaction(Integer bookId, Integer studentId) throws TxnServiceException {

        /**
         *  1] Student is valid entity.
         *  2] Book is issued to this particular student.
         *  3] Calculate a Fine.
         *  4] Create a return transaction.
         *  5] Make the book available.
         */

        Student student = studentService.findStudentByStudentId(studentId);
        if (student == null) {
            throw new TxnServiceException("Student is Not Present.");
        }

        List<Book> bookList = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if (bookList == null || bookList.size() != 1) {
            throw new TxnServiceException("Book is Not Present.");
        }

        if (bookList.get(0).getStudent().getId() != studentId) {
            throw new TxnServiceException("Book not issued to this Student.");
        }

        // calculate a fine.
        // issued date ==> create date of Txn.
        // returned date ==> Today's Date
        // returning days = 20
        // max days = 14
        // 20-14 = 6 ==> 6 Days * 1Rs ==> fine

        // Query : select * from Transaction where studentId = ? and bookId = ? and transactionType = 'ISSUE' order by txn_date desc limit 1;

        // S1 ==> B1 Issue : 10 May
        // S1 ==> B1 Return : 15 May
        // S1 ==> B1 Issue : 20 May

        Transaction issuedTransaction =
                transactionRepository.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc
                        (student, bookList.get(0), TransactionType.ISSUE);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN)
                .externalTxnId(UUID.randomUUID().toString())
                .book(bookList.get(0))
                .student(student)
                .payment(calculateFine(issuedTransaction))
                .build();

        transactionRepository.save(transaction);
        bookList.get(0).setStudent(null);
        bookService.create(bookList.get(0));

        return transaction.getExternalTxnId();


    }

    private double calculateFine(Transaction issuedTransaction) {

        long issueTime = issuedTransaction.getTransactionDate().getTime();
        long returnTime = System.currentTimeMillis();

        long diff = returnTime - issueTime;
        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed >= number_of_days)
            return (daysPassed - number_of_days) * 1.0;
        return 0.0;

    }
}
