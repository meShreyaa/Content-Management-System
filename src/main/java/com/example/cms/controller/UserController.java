package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.cms.dto.UserRequest;
import com.example.cms.entity.User;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ErrorStructure;
import com.example.cms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@RestController
public class UserController {

	private UserService userService;
	
	@PostMapping(value = "users/register")
	@Operation(description = "The endpoint is used to add a new product in the Database", responses = {
			@ApiResponse(responseCode = "200", description = "User Registered Successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Inputs", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	public ResponseEntity<ResponseStructure<UserResponse>> register(@RequestBody UserRequest userRequest){
		return userService.save(userRequest); 
	}
	
	@GetMapping("/test")
	public String test() {
		return "Hello From cms";
	}
//	
    @GetMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(@PathVariable int userId ){
		return userService.findById(userId);
	}
	@DeleteMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> delete(@PathVariable int userId){
		return userService.deleteById(userId);
	}
	
}
