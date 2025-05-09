package com.booklidio.booklidio_spring_backend.Inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173", methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
        RequestMethod.OPTIONS }, allowedHeaders = "*", maxAge = 3600)
public class BookController {
    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getBooks() {
        try {
            return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all books", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBook/{isbn}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable("isbn") String isbn) {
        try {
            return new ResponseEntity<>(bookService.getBook(isbn), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting book with ISBN: {}", isbn, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBook/")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        try {
            Book book = new Book();
            book.setIsbn(bookDTO.getIsbn());
            book.setTitle(bookDTO.getTitle());
            book.setGrade(bookDTO.getGrade());
            book.setNewPrice(bookDTO.getNewPrice());
            book.setUsedPrice(bookDTO.getUsedPrice());
            book.setCostPrice(bookDTO.getCostPrice());
            bookService.addBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error adding book", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editBook/{isbn}")
    public ResponseEntity<Book> editBook(@PathVariable("isbn") String isbn, @RequestBody BookDTO bookDTO) {
        try {
            Book book = new Book();
            book.setIsbn(bookDTO.getIsbn());
            book.setTitle(bookDTO.getTitle());
            book.setGrade(bookDTO.getGrade());
            book.setNewPrice(bookDTO.getNewPrice());
            book.setUsedPrice(bookDTO.getUsedPrice());
            book.setCostPrice(bookDTO.getCostPrice());
            bookService.editBook(isbn, book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error editing book with ISBN: {}", isbn, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/duplicateBook/{isbn}")
    public ResponseEntity<Book> duplicateBook(@PathVariable("isbn") String isbn) {
        try {
            bookService.duplicateBook(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error duplicating book with ISBN: {}", isbn, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteBook/{isbn}")
    public ResponseEntity<Book> deleteBook(@PathVariable("isbn") String isbn) {
        try {
            bookService.deleteBook(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting book with ISBN: {}", isbn, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
