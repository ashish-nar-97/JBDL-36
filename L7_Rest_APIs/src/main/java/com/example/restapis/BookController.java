package com.example.restapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    HashMap<String, Book> bookHashMap = new HashMap<>();

//    1] GET : Retrieve some information from server.
//    2] POST : Saving some information on server side.
//    3] DELETE : Deleting the information on the server.
//    4] PUT : Updating the already existing information on the server.

    // localhost:8080/book?bid=1
    @GetMapping("/book")
//    @RequestMapping(value = "/book", method = {RequestMethod.GET})
    public Book getBook(@RequestParam("bid") String bid){
        logger.info("received a book id {}",bid);
        return bookHashMap.get(bid);
    }

    @PostMapping("/book")
    public void insertBook(@RequestBody Book book, @RequestParam String message){
        logger.info("Book received : {}", book);
        bookHashMap.put(book.getId(), book);
    }

    @GetMapping("/book/all")
    public List<Book> getBooks(){
        return bookHashMap.values().stream().collect(Collectors.toList());
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book, @RequestParam String bookId){
        bookHashMap.put(bookId, book);
        return bookHashMap.get(bookId);
    }

    @DeleteMapping("/book")
    public Book deleteBook(@RequestParam("bid") String bookId){
        return bookHashMap.remove(bookId);
    }

    @GetMapping("/book/{message}")
    public Book getBookById(@RequestParam("bookId") String id, @PathVariable("message") String message){
        logger.info(message);
        return  bookHashMap.get(id);
    }

    @GetMapping("/get-book")
    public Book getBookWithOptional(@RequestParam(value = "bid", required = false, defaultValue = "2") String bid){
        logger.info("received a book id {}",bid);
        return bookHashMap.get(bid);
    }



    // No 2 APIs should be similar.
    // ==> same path.
    // ==> same Http Method.

    //    @PostMapping("/book")
//    public void insertBook(@RequestParam("bid") Integer bid,
//                           @RequestParam("name") String name,
//                           @RequestParam("author") String author,
//                           @RequestParam("cost") Integer cost){
//
//    }

    // Less Parameters or length of param is More : PathVariable

    // More params or length of params is less : RequestParam

}
