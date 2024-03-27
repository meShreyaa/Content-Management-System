package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.UserRequest;
import com.example.cms.entity.User;
import com.example.cms.findrequestdto.FindUserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> save(UserRequest userRequest);

	//ResponseEntity<ResponseStructure<UserResponse>> findByUserEmail(String userEmail,FindUserRequest findRequest);
	ResponseEntity<ResponseStructure<UserResponse>> findById(int userId);
	ResponseEntity<ResponseStructure<UserResponse>> deleteById(int userId);
}
