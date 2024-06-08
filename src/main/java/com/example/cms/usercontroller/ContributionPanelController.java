package com.example.cms.usercontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.ContributionPanelResponce;
import com.example.cms.userrepository.BlogReposatory;
import com.example.cms.userrepository.UserRepository;
import com.example.cms.userservice.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ContributionPanelController {
	
	private ContributionPanelService contributionPanelService;
	
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponce>> addContributers(@PathVariable int userId,@PathVariable int panelId)
	{
		return contributionPanelService.addContributers(userId,panelId);
	}
//	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
//	public ResponseEntity<ResponseStructure<ContributionPanelResponce>> removeUserFromContribution(@PathVariable int userId,@PathVariable int panelId)
//	{
//	    return contributionPanelService.removeUserFromContribution(userId,panelId);	
//	}
	
	
	
	

}
