package com.clone.reddit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.reddit.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByUsername(String username);

}
