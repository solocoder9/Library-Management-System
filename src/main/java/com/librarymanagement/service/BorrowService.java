package com.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.dto.BorrowRequestDTO;
import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.BorrowedBook;
import com.librarymanagement.entity.User;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.repository.BorrowedBookRepository;
import com.librarymanagement.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BorrowService {
	@Autowired
	private BorrowedBookRepository borrowedBookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	public String borrowBook(BorrowRequestDTO request) {
		Optional<User> userOpt = userRepository.findById(request.getUserId());
		Optional<Book> bookOpt = bookRepository.findById(request.getBookId());

		if (userOpt.isEmpty() || bookOpt.isEmpty()) {
			return "User or Book not found!";
		}

		Book book = bookOpt.get();
		if (book.getAvailableCopies() <= 0) {
			return "No copies available for this book.";
		}

		BorrowedBook borrowedBook = new BorrowedBook();
		borrowedBook.setUser(userOpt.get());
		borrowedBook.setBook(book);
		borrowedBook.setBorrowedAt(LocalDateTime.now());
		borrowedBook.setDueDate(LocalDateTime.now().plusDays(15));
		borrowedBookRepository.save(borrowedBook);

		book.setAvailableCopies(book.getAvailableCopies() - 1);
		bookRepository.save(book);

		return "Book borrowed successfully!";
	}
	
	public String returnBook(Long bookId, String username) {
	    User user = userRepository.findByUserid(username)
	            .orElseThrow(() -> new EntityNotFoundException("User not found"));
	    
	    BorrowedBook borrowedBook = borrowedBookRepository
	            .findByBookIdAndUserIdAndReturnedFalse(bookId, user.getId())
	            .orElseThrow(() -> new EntityNotFoundException("No active borrowing record found"));

	    borrowedBook.setReturned(true);
	    borrowedBookRepository.save(borrowedBook);

	    Book book = bookRepository.findById(bookId)
	            .orElseThrow(() -> new EntityNotFoundException("Book not found"));
	    
	    book.setAvailableCopies(book.getAvailableCopies() + 1);
	    bookRepository.save(book);

	    return "Book returned successfully";
	}
	
}
