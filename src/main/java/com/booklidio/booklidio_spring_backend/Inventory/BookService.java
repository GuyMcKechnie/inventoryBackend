package com.booklidio.booklidio_spring_backend.Inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching books", e);
            throw new RuntimeException("Error fetching books", e);
        }
    }

    public Optional<Book> getBook(String isbn) {
        try {
            return bookRepository.findByIsbn(isbn);
        } catch (Exception e) {
            logger.error("Error fetching book with ISBN: " + isbn, e);
            throw new RuntimeException("Error fetching book with ISBN: " + isbn, e);
        }
    }

    public void addBook(Book book) {
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            logger.error("Error adding book", e);
            throw new RuntimeException("Error adding book", e);
        }
    }

    public void editBook(String isbn, Book book) {
        try {
            Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
            if (existingBook.isEmpty()) {
                logger.error("Error editing book with ISBN: " + isbn + " - Book not found");
                throw new RuntimeException("Error editing book with ISBN: " + isbn + " - Book not found");
            }
            existingBook.get().setTitle(book.getTitle());
            existingBook.get().setGrade(book.getGrade());
            existingBook.get().setNewPrice(book.getNewPrice());
            existingBook.get().setUsedPrice(book.getUsedPrice());
            existingBook.get().setCostPrice(book.getCostPrice());
            bookRepository.save(existingBook.get());
        } catch (Exception e) {
            logger.error("Error editing book with ISBN: " + isbn + " - Book not found");
            throw new RuntimeException("Error editing book with ISBN: " + isbn + " - Book not found");
        }
    }

    public void duplicateBook(String isbn) {
        try {
            Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
            if (existingBook.isEmpty()) {
                logger.error("Error duplicating book with ISBN: {} - Book not found", isbn);
                throw new RuntimeException("Error duplicating book with ISBN: " + isbn + " - Book not found");
            }
            Book book = existingBook.get();
            Book duplicatedBook = new Book();
            duplicatedBook.setTitle(book.getTitle());
            duplicatedBook.setGrade(book.getGrade());
            duplicatedBook.setNewPrice(book.getNewPrice());
            duplicatedBook.setUsedPrice(book.getUsedPrice());
            duplicatedBook.setCostPrice(book.getCostPrice());
            bookRepository.save(duplicatedBook);
        } catch (Exception e) {
            logger.error("Error duplicating book with ISBN: " + isbn, e);
            throw new RuntimeException("Error duplicating book with ISBN: " + isbn, e);
        }
    }

    public void deleteBook(String isbn) {
        try {
            Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
            if (existingBook.isEmpty()) {
                logger.error("Error deleting book with ISBN: {} - Book not found", isbn);
                throw new Exception("Error deleting book with ISBN: " + isbn + " - Book not found");
            }
            bookRepository.delete(existingBook.get());
        } catch (Exception e) {
            logger.error("Error deleting book with ISBN: " + isbn, e);
            throw new RuntimeException("Error deleting book with ISBN: " + isbn, e);
        }
    }
}
