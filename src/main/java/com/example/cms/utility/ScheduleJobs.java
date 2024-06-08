package com.example.cms.utility;

import java.time.LocalDateTime;

import org.hibernate.mapping.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.cms.enums.PostType;
import com.example.cms.usermodel.BlogPost;
import com.example.cms.userrepository.BlogPostReposatory;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

import static java.lang.System.out;
@Component
@AllArgsConstructor
public class ScheduleJobs 
{    
	private BlogPostReposatory blogPostReposatory;
	
	
	@Scheduled(fixedDelay = 1000l)
    public void publishSchedulerBlogPost()
    {
    	out.println("hello");
    	java.util.List<BlogPost> postType = blogPostReposatory.findAllByPublishScheduleDateTimeLessThanEqualAndPostType(LocalDateTime.now(),PostType.SCHEDULE);

		postType.stream().map(post->{
			post.setPostType(PostType.PUBLISHED);
			return post;
		}).collect(Collectors.toList());
		blogPostReposatory.saveAllAndFlush(postType);
    	
    }
}
