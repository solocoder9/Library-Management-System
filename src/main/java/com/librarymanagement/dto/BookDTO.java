package com.librarymanagement.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
	private String title;
	private String author;
	private String isbn;
	private LocalDate publishedDate;
	private int pages;
	private int availableCopies;
}
