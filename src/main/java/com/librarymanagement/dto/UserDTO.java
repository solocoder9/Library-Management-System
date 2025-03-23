package com.librarymanagement.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {
	
	private String userid;
    private String username;
    private String password;
    private String role;
}
