package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.entity.User;
import com.example.cms.exception.EmailAlreadyExists;
import com.example.cms.exception.UserNotFoundById;
import com.example.cms.findrequestdto.FindUserRequest;
//import com.example.cms.findrequestdto.FindUserRequest;
import com.example.cms.repository.UserRepository;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;
	private ResponseStructure<UserResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> save(UserRequest userRequest) {
		if (repository.existsByUserEmail(userRequest.getUserEmail()))
			throw new EmailAlreadyExists("Failed to register user");

		User user = repository.save(mapToUserEntity(userRequest, new User()));

		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
				.setMessage("User registered Successfully").setData(mapToUserResponse(user)));
	}

	

	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setUserName(userRequest.getUserName());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserPassword(passwordEncoder.encode(userRequest.getUserPassword()));
		return user;
	}
	


//	@Override
//	public ResponseEntity<ResponseStructure<UserResponse>> findByUserEmail(String userEmail, FindUserRequest findRequest) {
//		
//		if(!repository.existsByUserEmail(findRequest.getUserEmail()))
//			throw new UserNotFoundById("No User exists with given email");
//		
//		User foundUser=repository.findByUserEmail(userEmail, new FindUserRequest());
//		
//		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
//				.setMessage("User registered Successfully").setData(mapToUserResponse(foundUser)));
//		
//	}


	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userId(user.getUserId()).userName(user.getUserName())
				.userEmail(user.getUserEmail()).createdAt(user.getCreatedAt()).lastModifiedAt(user.getLastModifiedAt()).build();
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userId, FindUserRequest findRequest) {
		if(!repository.existsById(findRequest.getUserId()))
			throw new UserNotFoundById("No User exists with given email");
		
		User foundUser=repository.findById(userId, new FindUserRequest());
		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
			.setMessage("User registered Successfully").setData(mapToUserResponse(foundUser)));

		
		
	}


//	@Override
//	public ResponseEntity<ResponseStructure<User>> save(UserRequest userRequest) {
//		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
//				.setMessage("User Registered Sucessfully")
//				.setData(repository.save(mapToUser(userRequest, new User()))));
//	}
//	
//	private User mapToUser(UserRequest userRequest, User user) {
//		user.setUserName(userRequest.getUserName());
//		user.setUserEmail(userRequest.getUserEmail());
//		user.setUserPassword(userRequest.getUserPassword());
//		return user;
//	}

}
