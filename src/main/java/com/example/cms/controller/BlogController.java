package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@RestController
public class BlogController {

	private BlogService blogService;
	
	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@PathVariable int userId,@RequestBody BlogRequest blogRequest){
		return blogService.createBlog(userId, blogRequest);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> finBlogById(@PathVariable int blogId){
		return blogService.findById(blogId);
	}
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(@PathVariable int blogId, @RequestBody BlogRequest blogRequest){
		return blogService.updateBlog(blogId, blogRequest);
	}
	@GetMapping("/titles/{title}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> titleAvailability(@PathVariable String title){
		return blogService.findByTitle(title);
	}
}
