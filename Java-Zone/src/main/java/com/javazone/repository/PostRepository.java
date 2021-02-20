package com.javazone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javazone.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
