package com.example.cms.userservice;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.ContributionPanelResponce;
import com.example.cms.usercontroller.ContributionPanelController;
import com.example.cms.utility.ResponseStructure;

public interface ContributionPanelService {

	ResponseEntity<ResponseStructure<ContributionPanelResponce>> addContributers(int userId, int panelId);

	//ResponseEntity<ResponseStructure<ContributionPanelResponce>> removeUserFromContribution(int userId, int panelId);

}
