package com.javazone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javazone.dto.PostRequest;
import com.javazone.service.PostService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
@CrossOrigin("http://localhost:4200")
public class PostController {

	private final PostService postService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
		String resp=postService.createPost(postRequest);
		return new ResponseEntity<String>(resp, HttpStatus.CREATED);
	}

	@GetMapping(path="/getPost/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PostRequest> getPost(@PathVariable("id") long id) {
		PostRequest postRequest=postService.getPost(id);
		return new ResponseEntity<PostRequest>(postRequest, HttpStatus.OK);
	}

	@GetMapping(path="/getAllPosts",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<PostRequest>> getAllPost(){
		List<PostRequest> postRequest=postService.getAllPost();
		return new ResponseEntity<List<PostRequest>>(postRequest, HttpStatus.OK);
	}
}

