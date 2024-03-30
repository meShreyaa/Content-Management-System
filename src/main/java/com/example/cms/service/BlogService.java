package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogRequest;
import com.example.cms.entity.Blog;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId, BlogRequest blogRequest);
	ResponseEntity<ResponseStructure<BlogResponse>> findById(int blogId);
	ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(int blogId, BlogRequest blogRequest);
	ResponseEntity<Boolean> findByTitle(String title);
	
}
