package com.example.cms.userservice.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostRequst;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogPostNotFoundException;
import com.example.cms.exception.UserNotFoundById;
import com.example.cms.usermodel.BlogPost;
import com.example.cms.userrepository.BlogPostReposatory;
import com.example.cms.userrepository.BlogReposatory;
import com.example.cms.userrepository.ContributionPanelReposatory;
import com.example.cms.userrepository.UserRepository;
import com.example.cms.userservice.BlogPostService;
import com.example.cms.userservice.exception.IllegalAccessReequestException;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

	private BlogReposatory blogReposatory;
	private BlogPostReposatory blogPostReposatory;
	private UserRepository userrepository;
	private ResponseStructure<BlogPostResponse> responseStructure;
	private ContributionPanelReposatory contributionPanelReposatory;


	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(BlogPostRequst blogPostRequst, int blogId) 
	{
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userrepository.findByEmail(email).map(owner->{
			return blogReposatory.findById(blogId).map(blog-> {
				if(email.equals(blog.getUser().getEmail())||contributionPanelReposatory.existsByPanelIdAndUsers(blog.getContributionPanel().getPanelId(),owner)) {
					BlogPost blogPost=new BlogPost();
					blogPost.setBlog(blog);
					blogPostReposatory.save(mapToBlogPostEntity(blogPostRequst, blogPost));
					return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
							.setMessage("BlogPost Added ").setData(mapToBlogPostResponse(blogPost)));
				}else
					throw new IllegalAccessReequestException("Acces not parmitted");

			}).orElseThrow(()-> new BlogPostNotFoundException("Not able to create Blog post"));
		}).orElseThrow(()->new UserNotFoundById("user not found By ID"));

	}

	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost)
	{
		return BlogPostResponse.builder().postId(blogPost.getPostId()).title(blogPost.getTitle()) 
				.subTitle(blogPost.getSubTitle()).postType(blogPost.getPostType())
				.summary(blogPost.getSummary())
				.createAt(blogPost.getCreateAt()).createBy(blogPost.getCreateBy()).lastModifiedAt(blogPost.getLastModifiedAt())
				.lastModifiedBy(blogPost.getLastModifiedBy()).build();
	}

	private BlogPost mapToBlogPostEntity(BlogPostRequst blogPostRequst,BlogPost blogPost)
	{
		blogPost.setTitle(blogPostRequst.getTitle());
		blogPost.setPostType(PostType.DRAFT);
		blogPost.setSubTitle(blogPostRequst.getSubTitle());
		blogPost.setSummary(blogPostRequst.getSummary());
		return blogPost;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPost(BlogPostRequst postRequst, int postId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userrepository.findByEmail(email).map(owner->{
			return blogPostReposatory.findById( postId).map(exstigPost->{
				if(email.equals(exstigPost.getBlog().getUser().getEmail())||contributionPanelReposatory.existsByPanelIdAndUsers(exstigPost.getBlog().getContributionPanel().getPanelId(), owner))
//				exstigPost.setTitle(postRequst.getTitle());
//				exstigPost.setSubTitle(postRequst.getSubTitle());
//				exstigPost.setSummary(postRequst.getSummary());
				blogPostReposatory.save(mapToBlogPostEntity(postRequst, exstigPost));
				return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
						.setMessage("Updated")
						.setData(mapToBlogPostResponse(exstigPost)));

			}).orElseThrow(()->new BlogPostNotFoundException("Not able to update..!"));
		}).orElseThrow(()->new UserNotFoundById("User Not found By Id"));

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId)
	{
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userrepository.findByEmail(email).map(owner->{
			return blogPostReposatory.findById(postId).map(delete->{
				if(email.equals(delete.getBlog().getUser().getEmail())||contributionPanelReposatory.existsByPanelIdAndUsers(delete.getBlog().getContributionPanel().getPanelId(), owner))
				{
					blogPostReposatory.delete(delete);
					return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
							.setMessage("Deleted")
							.setData(mapToBlogPostResponse(delete)));
				}else 
					throw new IllegalAccessReequestException("Acces not permitted");
			}).orElseThrow(()->new BlogPostNotFoundException("Blog Id Not Found"));
		}).orElseThrow(()->new UserNotFoundById("User Not Found By ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostById(int postId) {
		// TODO Auto-generated method stub
		return blogPostReposatory.findById(postId).map(blogPost->{
			 return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value()).setMessage("blog post fetched By ID")
					 .setData(mapToBlogPostResponse(blogPost)));
		}).orElseThrow(()->new BlogPostNotFoundException("BlogPost not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostByPublished(int postId) {
	
		return blogPostReposatory.findByPostIdAndPostType(postId,PostType.PUBLISHED).map(blogPst->{
		return  ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value()).setMessage("Post found")
				 .setData(mapToBlogPostResponse(blogPst)));
			
	}).orElseThrow(()-> new BlogPostNotFoundException("BlogPost Not Found By Id"));
	}

}
