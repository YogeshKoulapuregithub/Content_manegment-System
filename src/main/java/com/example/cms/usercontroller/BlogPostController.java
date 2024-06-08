package com.example.cms.usercontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogPostRequst;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.userservice.BlogPostService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class BlogPostController {

	private BlogPostService blogPostService;

	@PostMapping("/blogs/{blogId}/blogposts")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(@RequestBody BlogPostRequst blogPostRequst,@PathVariable int blogId)
	{
		return blogPostService.createDraft(blogPostRequst,blogId);
	}
	
	@PutMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPost(@RequestBody BlogPostRequst postRequst,@PathVariable int postId)
	{
		return blogPostService.updateBlogPost(postRequst,postId);
	}
	@DeleteMapping(" /blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(@PathVariable int postId)
	{
		return blogPostService.deleteBlogPost(postId);
	}
	@GetMapping("/blog-Posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostById(@PathVariable int postId)
	{
		return blogPostService.getBlogPostById(postId);
	}
	@GetMapping("/blog/{postId}/published")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostByPublished(@PathVariable int postId)
	{
		return blogPostService.getBlogPostByPublished(postId);
	}
	

}
