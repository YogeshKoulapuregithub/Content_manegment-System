package com.example.cms.userservice;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogRequst;
import com.example.cms.dto.BlogResponce;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponce>> blogRegistration(BlogRequst blogRequst,int userId);

	ResponseEntity<ResponseStructure<BlogResponce>> findBlog(int blogId);

	ResponseEntity<ResponseStructure<BlogResponce>> updateBlog(int blogId,BlogRequst blogRequst);
	
	ResponseEntity<Boolean> checkTitle(String title);

}
