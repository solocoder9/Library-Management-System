package com.librarymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.entity.BorrowedBook;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
	
	Optional<BorrowedBook> findByBookIdAndUserIdAndReturnedFalse(Long bookId, Long userId);
}