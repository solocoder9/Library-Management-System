package com.librarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.librarymanagement.service.BookService;

@Controller
@RequestMapping("/ui")
public class ViewController {

    private final BookService bookService;

    public ViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book-details";
    }
}