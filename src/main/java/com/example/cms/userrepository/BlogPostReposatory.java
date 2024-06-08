package com.example.cms.userrepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.enums.PostType;
import com.example.cms.usermodel.BlogPost;
import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.usermodel.User;
@Repository
public interface BlogPostReposatory extends JpaRepository<BlogPost, Integer> {

	Optional<BlogPost> findByPostIdAndPostType(int postId, PostType published);
	
	List<BlogPost> findAllByPublishScheduleDateTimeLessThanEqualAndPostType(LocalDateTime now, PostType schedule);
  
	
}
