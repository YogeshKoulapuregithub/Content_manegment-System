package com.example.cms.usercontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.usermodel.User;
import com.example.cms.userservice.UserService;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class UserController {
	
	private UserService userService;
	
	
	@GetMapping("/test")
	public String test()
	{
		return "hello From content Manegment System";
	}
	
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> userRegistration(@RequestBody @Valid UserRequest user)
	{
		return userService.userRegistration(user);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> softDeleteUser( @PathVariable int userId)
	{
		return userService.softDeleteUser(userId);
		
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById( @PathVariable int userId)
	{
       return userService.findById(userId);
	}
}
	

