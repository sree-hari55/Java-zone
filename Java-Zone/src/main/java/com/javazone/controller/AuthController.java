package com.javazone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javazone.dto.AuthenticationResponse;
import com.javazone.dto.LoginRequest;
import com.javazone.dto.RegistrationRequest;
import com.javazone.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping(path = "/signUp",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
		String resp=authService.registerUser(registrationRequest);
		return new ResponseEntity<String>(resp,HttpStatus.CREATED);
	}
	
	
	@PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest){
		AuthenticationResponse authenticationResponse=authService.loginUser(loginRequest);
		return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.OK);
	}
}
