package com.example.cms.usercontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PublishRequst;
import com.example.cms.dto.PublishResponce;
import com.example.cms.userservice.PublishService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class PublishController {
	private PublishService publishService;

    @PostMapping("/blogposts/{postId}/publishes")
	public ResponseEntity<ResponseStructure<PublishResponce>> createPublish(@RequestBody PublishRequst publishRequst,@PathVariable int postId)
	{
		return publishService.publishBlogPost(publishRequst,postId);
	}
    
    @PutMapping("/blog-post/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> unPublishBlogPost(@PathVariable int postId)
	{
		return publishService.unPublishBlogPost(postId);
	}

}
