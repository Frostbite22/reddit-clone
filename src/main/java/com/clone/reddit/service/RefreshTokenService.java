package com.clone.reddit.service;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.clone.reddit.exceptions.SpringRedditException;
import com.clone.reddit.model.RefreshToken;
import com.clone.reddit.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository ; 
	
	public RefreshToken generateRefreshToken()
	{
		RefreshToken refreshToken = new RefreshToken() ; 
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		
		return refreshTokenRepository.save(refreshToken);
	}
	
	void validateRefreshToken(String token)
	{
		refreshTokenRepository.findByToken(token)
		.orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));
	}
	
	public void deleteRefreshToken(String token)
	{
		refreshTokenRepository.deleteByToken(token);
	}
}
