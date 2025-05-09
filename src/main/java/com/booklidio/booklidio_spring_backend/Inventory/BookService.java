package com.booklidio.booklidio_spring_backend.Inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public void addBook(Book book) throws Exception {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            System.out.println("Book with ISBN: " + book.getIsbn() + " already exists");
            throw new Exception("Book with ISBN: " + book.getIsbn() + " already exists");
        }
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editBook(String isbn, Book book) throws Exception {
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook.isPresent()) {
            existingBook.get().setTitle(book.getTitle());
            existingBook.get().setGrade(book.getGrade());
            existingBook.get().setNewPrice(book.getNewPrice());
            existingBook.get().setUsedPrice(book.getUsedPrice());
            existingBook.get().setCostPrice(book.getCostPrice());
            bookRepository.save(existingBook.get());
        } else {
            throw new Exception("Edit: Book not found");
        }
    }

    public void duplicateBook(String isbn) throws Exception {
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            Book duplicatedBook = new Book();
            duplicatedBook.setTitle(book.getTitle());
            duplicatedBook.setGrade(book.getGrade());
            duplicatedBook.setNewPrice(book.getNewPrice());
            duplicatedBook.setUsedPrice(book.getUsedPrice());
            duplicatedBook.setCostPrice(book.getCostPrice());
            bookRepository.save(duplicatedBook);
        } else {
            throw new Exception("Duplicate: Book not found");
        }
    }

    public void deleteBook(String isbn) throws Exception {
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook.isPresent()) {
            bookRepository.delete(existingBook.get());
        } else {
            throw new Exception("Delete: Book not found");
        }
    }
}
