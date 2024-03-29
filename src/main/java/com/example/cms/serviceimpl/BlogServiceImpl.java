package com.example.cms.serviceimpl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogRequest;
import com.example.cms.entity.Blog;
import com.example.cms.entity.User;
import com.example.cms.exception.BlogAlreadyExistsByTitle;
import com.example.cms.exception.BlogNotFoundById;
import com.example.cms.exception.TitleAlreadyExists;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserNotFoundById;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
	
	private BlogRepository blogRepository;
	private UserRepository userRepository;
	private ResponseStructure<BlogResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId, BlogRequest blogRequest) {
		return userRepository.findById(userId).map(user -> {
			if(blogRepository.existsByTitle(blogRequest.getTitle())){
					throw new TitleAlreadyExists("Failed to create The blog");
			}
			if(blogRequest.getTopics().length<1) {
				throw new TopicNotSpecifiedException("Failed to create The blog");
			}
			Blog blog=mapToBlogs(blogRequest);
//			blog.setUsers(Arrays.asList(user));
			user.getBlogs().add(blog);
			blogRepository.save(blog);
			userRepository.save(user);
			return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setMessage("Blog Added Successfully")
					.setData(mapToBlogResponse(blog)));
		}).orElseThrow(()-> new UserNotFoundById("Failed To Create Blog"));
		
	}

	private BlogResponse mapToBlogResponse(Blog blogres) {
		return BlogResponse.builder()
				.blogId(blogres.getBlogId())
				.title(blogres.getTitle())
				.topics(blogres.getTopics())
				.about(blogres.getAbout())
				.build();
	}

	private Blog mapToBlogs(BlogRequest blogRequest) {
		Blog blog = new Blog();
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());
		return blog;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findById(int blogId) {
		return blogRepository.findById(blogId).map(blog -> ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
				.setMessage("Blog fetched succefully")
				.setData(mapToBlogResponse(blog)))).orElseThrow(()-> new BlogNotFoundById("Failed to Fetch the blog"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(int blogId, BlogRequest blogRequest) {
		Blog blog = mapToBlogs(blogRequest);
		return blogRepository.findById(blogId).map(exBlog -> {
			blog.setBlogId(exBlog.getBlogId());
			exBlog=blogRepository.save(blog);
			return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setMessage("Block Updated Successfully")
					.setData(mapToBlogResponse(exBlog)));
		}).orElseThrow(() -> new UserNotFoundById("Unaable to Find User with the mentioned ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findByTitle(String title) {
		return blogRepository.findByTitle(title).map(blog -> ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
				.setMessage("true")
				.setData(mapToBlogResponse(blog)))).orElseThrow(()-> new BlogAlreadyExistsByTitle("False"));
	}

	

	

}
