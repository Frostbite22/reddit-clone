package com.clone.reddit.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clone.reddit.dto.RegisterRequest;
import com.clone.reddit.model.User;
import com.clone.reddit.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final PasswordEncoder passwordEncoder = null;
    private final UserRepository userRepository ;
	
	public void signup(RegisterRequest registerRequest)
	{
		User user = new User() ; 
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); 
		user.setCreated(Instant.now());
		user.setEnabled(false) ; 
		
		userRepository.save(user); 
		
	}
}
