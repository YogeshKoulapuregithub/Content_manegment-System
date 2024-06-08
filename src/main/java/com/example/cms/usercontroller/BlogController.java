package com.example.cms.usercontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogRequst;
import com.example.cms.dto.BlogResponce;
import com.example.cms.userservice.BlogService;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BlogController {
	
	private BlogService blogService;
	
	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponce>> blogRegistration(@RequestBody @Valid BlogRequst blogRequst,@PathVariable int userId)
	{
		return blogService.blogRegistration(blogRequst,userId);
	}
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponce>> findBlog(@PathVariable int blogId)
	{
		return blogService.findBlog(blogId);	
	}	
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponce>> updateBlog(@PathVariable int blogId,@RequestBody  BlogRequst blogRequst)
	{
		 return blogService.updateBlog(blogId,blogRequst);
	}
	@GetMapping("/title/{title}/blogs")
	public ResponseEntity<Boolean> checkTitle(@PathVariable String title)
	{
		return blogService.checkTitle(title);
	}

}
