package com.example.cms.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.usermodel.User;
@Repository
public interface ContributionPanelReposatory extends JpaRepository<ContributionPanel,Integer>{

	boolean existsByPanelIdAndUsers(int panelId, User owner);
	

}
