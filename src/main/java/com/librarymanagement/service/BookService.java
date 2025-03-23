package com.librarymanagement.service;

import com.librarymanagement.dto.BookDTO;
import com.librarymanagement.entity.Book;
import com.librarymanagement.exception.EntityNotFoundException;
import com.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add a new book
    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishedDate(bookDTO.getPublishedDate());
        book.setPages(bookDTO.getPages());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));
    }

    // Update a book
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setPublishedDate(bookDTO.getPublishedDate());
        existingBook.setPages(bookDTO.getPages());
        existingBook.setAvailableCopies(bookDTO.getAvailableCopies());

        return bookRepository.save(existingBook);
    }

    // Delete a book
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}