package com.javazone.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.javazone.dto.PostRequest;
import com.javazone.exception.PostNotFoundException;
import com.javazone.models.Post;
import com.javazone.repository.PostRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{


	private final AuthService authService;
	private final PostRepository postRepository;


	@Transactional
	@Override
	public String createPost(PostRequest postRequest) {
		try {
			Post post=mapFromDtoToPost(postRequest);
			postRepository.save(post);
			return "post has been created";
		}catch (Exception e) {
			return "post has failed";
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public PostRequest getPost(long id) {
		Post post=postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("post is not found"+id));
		PostRequest postRequest=mapFromPostToDto(post);
		return postRequest;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public List<PostRequest> getAllPost() {
		List<Post> posts=postRepository.findAll();
		List<PostRequest> postRequest=posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
		return postRequest;
	}

	public Post mapFromDtoToPost(PostRequest postRequest) {
		Post post=new Post();
		post.setTitle(postRequest.getTitle());
		post.setContent(postRequest.getContent());
		User user=authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
		post.setUserName(user.getUsername());
		post.setCreatedOn(Instant.now());
		post.setUpdatedOn(Instant.now());
		return post;
	}

	public PostRequest mapFromPostToDto(Post post) {
		PostRequest postRequest=new PostRequest();
		postRequest.setPostId(post.getPostId());
		postRequest.setTitle(post.getTitle());
		postRequest.setContent(post.getContent());
		postRequest.setUserName(post.getUserName());
		return postRequest;
	}

}
