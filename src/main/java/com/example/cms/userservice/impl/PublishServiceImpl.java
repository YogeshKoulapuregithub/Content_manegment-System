package com.example.cms.userservice.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PublishRequst;
import com.example.cms.dto.PublishResponce;
import com.example.cms.dto.ScheduleRequst;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.BlogPostNotFoundException;
import com.example.cms.exception.InvalidPostStateException;
import com.example.cms.exception.InvalidPostStatusException;
import com.example.cms.exception.TimeDateNotValidException;
import com.example.cms.usermodel.BlogPost;
import com.example.cms.usermodel.Publish;
import com.example.cms.usermodel.Schedule;
import com.example.cms.userrepository.BlogPostReposatory;
import com.example.cms.userrepository.PublishReposatory;
import com.example.cms.userrepository.ScheduleReposatory;
import com.example.cms.userrepository.UserRepository;
import com.example.cms.userservice.PublishService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PublishServiceImpl implements PublishService {

	private PublishReposatory publishRepository;
	private BlogPostReposatory blogPostRepository;
	private ResponseStructure<PublishResponce> responseStructure;
	private ResponseStructure<BlogPostResponse> response;
	private ScheduleReposatory scheduleReposatory;

	@Override
	public ResponseEntity<ResponseStructure<PublishResponce>> publishBlogPost(PublishRequst publishRequest,
			int postId) {

		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return blogPostRepository.findById(postId).map(blogPost->{
			System.out.println(blogPost.getBlog().getUser().getEmail());
			System.out.println(blogPost. getCreateBy());
			if(!blogPost.getBlog().getUser().getEmail().equals(email)||(!blogPost.
					getCreateBy().equals(email)))
				throw new IllegalArgumentException("failed to publish the blog post");
			Publish publish=null;
			if(blogPost.getPublish()!=null) {
				publish=mapToPublishRequest(publishRequest,blogPost.getPublish());
			}
			else {
				publish=mapToPublishRequest(publishRequest,new  Publish());
			}
			if(publishRequest.getSchedule()!=null) {
				if(!publishRequest.getSchedule().getDateTime().isAfter(LocalDateTime.now())) {
					throw new TimeDateNotValidException("date and time is not valided");
				}
				if(publish.getSchedule()==null) {
					publish.setSchedule(scheduleReposatory.save(mapToschedule(publishRequest.getSchedule(), new Schedule())));
					blogPost.setPostType(PostType.SCHEDULE);
				}
				else{
					publish.setSchedule(scheduleReposatory.save(mapToschedule(publishRequest.getSchedule(), publish.getSchedule())));
					blogPost.setPostType(PostType.SCHEDULE);
				}
			}
			else {
				blogPost.setPostType(PostType.PUBLISHED);
			}
			publish.setBlogPost(blogPost);
			blogPost.setPublish(publish);
			publishRepository.save(publish);
			blogPostRepository.save(blogPost);
			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.CREATED.value())
					.setMessage("the blog post draft created successfully")
					.setData(mapToBlogPostResponse(publish)));

		}).orElseThrow(()-> new BlogNotFoundByIdException("blog post is not found by Id"));

	}


	PublishResponce mapToBlogPostResponse(Publish publish) {
		return  PublishResponce.builder()
				.publishId(publish.getPublishId())
				.seoTitle(publish.getSeoTitle())
				.seoDescription(publish.getSeoDescription())
				.seoTopics(publish.getSeoTopics())
				.createdAt(publish.getCreatedAt())
				.build();

	}
	public Publish mapToPublishRequest(PublishRequst publishRequest,Publish publish) {
		Publish p=new Publish();
		p.setSeoTitle(publishRequest.getSeoTitle());
		p.setSeoDescription(publishRequest.getSeoDescription());
		p.setSeoTopics(publishRequest.getSeoTopics());
		return p;

	}

	public Schedule mapToschedule(ScheduleRequst requst,Schedule schedule)
	{
		schedule.setDateTime(requst.getDateTime());
		return schedule;
	}
	public BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
		return BlogPostResponse.builder()
				.postId(blogPost.getPostId())
				.title(blogPost.getTitle())
				.subTitle(blogPost.getSubTitle())
				.summary(blogPost.getSummary())
				.createAt(blogPost.getCreateAt())
				.lastModifiedAt(blogPost.getLastModifiedAt())
				.createAt(blogPost.getCreateAt())
				.lastModifiedBy(blogPost.getLastModifiedBy())
				.postType(blogPost.getPostType())
				.build();
	}
	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> unPublishBlogPost(int postId) {

		return blogPostRepository.findById(postId).map(blogPost->{
			if(blogPost.getPostType()!=PostType.PUBLISHED) {
				throw new InvalidPostStateException("Post is not in PUBLISHED state.");
			}
			blogPost.setPostType(PostType.DRAFT);
			blogPostRepository.save(blogPost);
			return ResponseEntity.ok(response.setStatuscode(HttpStatus.CREATED.value())
					.setMessage("the blog post published into draft created successfully")
					.setData(mapToBlogPostResponse(blogPost)));

		}).orElseThrow(()->new BlogPostNotFoundException("BlogPost id is not found"));

	}
}
