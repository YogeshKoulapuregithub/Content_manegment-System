package com.example.cms.userrepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.usermodel.Blog;
import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.usermodel.User;

public interface BlogReposatory extends JpaRepository<Blog, Integer> {


	boolean existsByTitle(String title);
	boolean existsByUserAndContributionPanel(User owner, ContributionPanel panel);


}
