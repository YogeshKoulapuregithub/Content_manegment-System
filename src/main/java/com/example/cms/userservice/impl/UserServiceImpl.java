package com.example.cms.userservice.impl;



import com.example.cms.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.usermodel.User;
import com.example.cms.userservice.UserService;
import com.example.cms.utility.ResponseStructure;
import com.example.cms.userrepository.UserRepository;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepostiory;
	private ResponseStructure<UserResponse> response;
	private PasswordEncoder encoder;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> userRegistration(UserRequest user) {
		if(userRepostiory.existsByEmail(user.getUserEmail()))
			throw new UserAlreadyExistByEmailException("Failed to register user");
		User saveUser=userRepostiory.save(mapToUser(user));
		return ResponseEntity.ok(response.setStatuscode(HttpStatus.CREATED.value())
				.setMessage("user Register successfully")
				.setData(matToResponse(saveUser)));
	}


	private UserResponse matToResponse(User saveUser) {
		return UserResponse.builder().userId(saveUser.getUserId()).username(saveUser.getUsername()).userEmail(saveUser.getEmail()).createdAt(saveUser.getCreatedAt()).postModifiedAt(saveUser.getPostModifiedAt()).build();

	}


	public User mapToUser(UserRequest userRequest) {
		return User.builder().username(userRequest.getUserName())
				.email(userRequest.getUserEmail()).password(encoder.encode(userRequest.getUserPassword())).build();
	}
}
