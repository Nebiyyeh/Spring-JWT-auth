package com.neb.dto.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@NotBlank(message = "Please provide a firstname!")
    private String firstName;
	
	@NotBlank(message = "Please provide a lastname!")
	private String lastName;
	
	@NotBlank(message = "Please provide a username!")
	private String userName;
	
	@NotBlank(message = "Please provide a password!")
	private String password;
	
}
