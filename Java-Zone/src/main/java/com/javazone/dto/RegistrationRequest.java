package com.javazone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
	
	protected String userName;
	
	protected String email;
	
	protected String password;
}
