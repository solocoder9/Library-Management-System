package com.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
