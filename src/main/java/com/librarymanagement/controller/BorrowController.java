package com.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.librarymanagement.dto.BorrowRequestDTO;
import com.librarymanagement.service.BorrowService;
import com.librarymanagement.utils.JwtUtil;

@RestController
@RequestMapping
public class BorrowController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private BorrowService borrowService;

	@PostMapping("/borrow/{bookId}")
	public String borrowBook(@RequestBody BorrowRequestDTO request) {
		return borrowService.borrowBook(request);
	}
	
	@PostMapping("/return/{bookId}")
	public ResponseEntity<String> returnBook(@PathVariable Long bookId, 
	                                       @RequestHeader("Authorization") String token) {
	    String username = jwtUtil.extractUserid(token.replace("Bearer ", ""));
	    return ResponseEntity.ok(borrowService.returnBook(bookId, username));
	}
}
