package com.librarymanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowRequestDTO {
	private Long userId;
	private Long bookId;
}
