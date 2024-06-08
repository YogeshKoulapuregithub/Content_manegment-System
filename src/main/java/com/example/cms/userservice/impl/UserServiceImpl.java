package com.example.cms.userservice.impl;



import com.example.cms.exception.*;

import java.util.Optional;

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
		return UserResponse.builder().userId(saveUser.getUserId())
				.username(saveUser.getUsername()).userEmail(saveUser.getEmail())
				.createdAt(saveUser.getCreatedAt()).postModifiedAt(saveUser.getPostModifiedAt()).build();

	} 


	public User mapToUser(UserRequest userRequest) {
		User u=new User();
		u.setEmail(userRequest.getUserEmail());
		u.setPassword(encoder.encode(userRequest.getUserPassword()));
		u.setUsername(userRequest.getUserName());
		return u;
				}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> softDeleteUser(int userId) {
		User user=userRepostiory.findById(userId).orElseThrow(()->new UserNotFoundById("The UserId is not prasent in the database"));
		user.setDeleted(true);
		userRepostiory.save(user);

		return ResponseEntity.ok(response.setStatuscode(HttpStatus.OK.value())
				.setMessage("user softly deleted successfully")
				.setData(matToResponse(user)));

	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userId) {
		// TODO Auto-generated method stub
		return	userRepostiory.findById(userId)
				.map(user->ResponseEntity.ok
						(response.setStatuscode(HttpStatus.OK.value())
								.setMessage("user is found by Id")
								.setData(matToResponse(user)))).orElseThrow(()->new UserNotFoundById("User ids not Found"));

	}
}
