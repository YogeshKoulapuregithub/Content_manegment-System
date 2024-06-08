package com.example.cms.userservice;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogPostRequst;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogPostService {

	ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(BlogPostRequst blogPostRequst, int blogId);

	ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPost(BlogPostRequst postRequst, int postId);

	ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId);

	ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostById(int postId);

	ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostByPublished(int postId);

}
