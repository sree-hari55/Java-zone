package com.javazone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javazone.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String username);

}
