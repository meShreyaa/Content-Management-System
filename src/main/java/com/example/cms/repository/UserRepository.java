package com.example.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.User;
import com.example.cms.findrequestdto.FindUserRequest;

public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByUserEmail(String userEmail);

	Optional<User> findByUserEmail(String userEmail);

	//User findByUserEmail(String userEmail, FindUserRequest findUserRequest);

	//



	
}
