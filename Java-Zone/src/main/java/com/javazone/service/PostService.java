package com.javazone.service;

import java.util.List;

import com.javazone.dto.PostRequest;

public interface PostService {

	public String createPost(PostRequest postRequest);
	public PostRequest getPost(long id);
	public List<PostRequest> getAllPost();
}
