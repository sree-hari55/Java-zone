package com.javazone.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javazone.dto.AuthenticationResponse;
import com.javazone.dto.LoginRequest;
import com.javazone.dto.RegistrationRequest;
import com.javazone.jwttokenconfig.JWTProvider;
import com.javazone.models.User;
import com.javazone.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final JWTProvider jwtProvider;

	public String registerUser(RegistrationRequest registrationRequest) {
		try {
			User user=new User();
			user.setUserName(registrationRequest.getUserName());
			user.setPassword(encodePassword(registrationRequest.getPassword()));
			user.setEmail(registrationRequest.getEmail());
			userRepository.saveAndFlush(user);
			return "User has been created sucessfully..........";
		}catch (Exception e) {
			return "already user exit with these name"+registrationRequest.getUserName();
		}
	}

	public String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}


	public AuthenticationResponse loginUser(LoginRequest loginRequest) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
				loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token= jwtProvider.generateToken(authentication);
		return new AuthenticationResponse(token,loginRequest.getUserName());
	}

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
				getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
	}
}
