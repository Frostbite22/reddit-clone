package com.clone.reddit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

import com.clone.reddit.dto.RegisterRequest;
import com.clone.reddit.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
	private final AuthService authService ; 
	//private final RefreshTokenService refreshTokenService ; 
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest)
	{
		authService.signup(registerRequest) ; 
		return new ResponseEntity<>("User registration Successful",OK); 
	}
	
}
