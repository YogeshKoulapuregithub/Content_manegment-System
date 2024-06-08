package com.example.cms.userservice.impl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogRequst;
import com.example.cms.dto.BlogResponce;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TitleNotFound;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserNotFoundById;
import com.example.cms.usermodel.Blog;
import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.usermodel.User;
import com.example.cms.userrepository.BlogReposatory;
import com.example.cms.userrepository.ContributionPanelReposatory;
import com.example.cms.userrepository.UserRepository;
import com.example.cms.userservice.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService{

	private BlogReposatory blogReposatory;
	private UserRepository userReposatory;
	private ResponseStructure<BlogResponce> responseStructure;
	private ContributionPanelReposatory contributionPanelRepo;

	@Override
	public ResponseEntity<ResponseStructure<BlogResponce>> blogRegistration(BlogRequst blogRequst,int userId) {
		return userReposatory.findById(userId).map(user->{
			if(blogReposatory.existsByTitle(blogRequst.getTitle()))
				throw new TitleNotFound("Tittle not found");

			if(blogRequst.getTopics().length<1)
				throw new TopicNotSpecifiedException("Failed to register blog topic");
			Blog blog = mapToBlog(blogRequst);
			blog.setUser(user);
			blog.setContributionPanel(contributionPanelRepo.save(new ContributionPanel()));
			blogReposatory.save(blog);

			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("Blog register seccusfully")
					.setData(mapToblogResponce(blog))
					);
		}).orElseThrow(()-> new UserNotFoundById("User not Found By Id"));
	}

	private BlogResponce mapToblogResponce(Blog saveBlog)
	{
		return BlogResponce.builder()
				.blogId(saveBlog.getBlogId())
				.title(saveBlog.getTitle())
				.topic(saveBlog.getTopic())
				.about(saveBlog.getAbout())
				.user(saveBlog.getUser())
				.build();
	}

	private Blog mapToBlog(BlogRequst blogRequst) {
		Blog b=new Blog();
		b.setTitle(blogRequst.getTitle());
		b.setTopic(blogRequst.getTopics());
		b.setAbout(blogRequst.getAbout());
		return b;

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponce>> findBlog(int blogId) {

		return blogReposatory.findById(blogId).map(b->{

			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("Id found Seccusfully")
					.setData(mapToblogResponce(b)));
		}).orElseThrow(()->new BlogNotFoundByIdException("Blog not found By Id"));

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponce>> updateBlog(int blogId, BlogRequst blogRequst) {
		return blogReposatory.findById(blogId).map(exstingBlog->{
			Blog updateblog = mapToBlog(blogRequst);
			blogReposatory.save(updateblog);
        return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
        		.setMessage("Blog updated seccusfully")
        		.setData(mapToblogResponce(updateblog)));
		}).orElseThrow(()-> new BlogNotFoundByIdException("Blog not found by Id"));		
	}

	@Override
	public ResponseEntity<Boolean> checkTitle(String title) {
		// TODO Auto-generated method stub
		boolean blogExist = blogReposatory.existsByTitle(title);
		return ResponseEntity.ok(blogExist);
	}

}
