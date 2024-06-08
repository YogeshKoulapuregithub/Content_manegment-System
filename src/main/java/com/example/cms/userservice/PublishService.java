package com.example.cms.userservice;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PublishRequst;
import com.example.cms.dto.PublishResponce;
import com.example.cms.utility.ResponseStructure;

public interface PublishService {

	ResponseEntity<ResponseStructure<PublishResponce>> publishBlogPost(PublishRequst publishRequst, int postId);

	ResponseEntity<ResponseStructure<BlogPostResponse>> unPublishBlogPost(int postId);

}
