package com.example.cms.userservice.impl;

import java.awt.Panel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.ContributionPanelResponce;
import com.example.cms.exception.UserNotFoundById;
import com.example.cms.usercontroller.ContributionPanelController;
import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.userrepository.BlogReposatory;
import com.example.cms.userrepository.ContributionPanelReposatory;
import com.example.cms.userrepository.UserRepository;
import com.example.cms.userservice.ContributionPanelService;
import com.example.cms.userservice.exception.IllegalAccessReequestException;
import com.example.cms.userservice.exception.PanelNotFoundByIdException;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class contributionPanelServiceImpl implements ContributionPanelService{

	private ContributionPanelReposatory contributionPanelRepo;
	private UserRepository userRepository;
	private BlogReposatory blogReposatory;
	private ResponseStructure<ContributionPanelResponce> responseStructure;


	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponce>> addContributers(int userId, int panelId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(owner->{
			return contributionPanelRepo.findById(panelId).map(panel->{
				if(!blogReposatory.existsByUserAndContributionPanel(owner, panel))
					throw new IllegalAccessReequestException("failed to add contrubuters");
				return userRepository.findById(userId).map(contributor->{
					panel.getUsers().add(contributor);
					contributionPanelRepo.save(panel);
					return ResponseEntity.ok(responseStructure.setData(mapToContributionPanelresponce(panel)).setStatuscode(HttpStatus.OK.value()).setMessage(""));
				}).orElseThrow(()-> new UserNotFoundById("invalid user Id"));

			}).orElseThrow(()->new PanelNotFoundByIdException("invlaid panal Id"));
		}).get();



		//		return userRepository.findByEmail(email).map(owner->{
		//			return contributionPanelRepo.findById(panelId).map(panel->{
		//				if(!blogReposatory.existsByUserAndContributionPanel(owner,panel))
		//					throw new IllegalAccessReequestException("Failed to add Contribution");
		//				return userRepository.findById(userId).map(contributer ->{
		//					panel.getUsers().add(contributer);
		//					contributionPanelRepo.save(panel);
		//					return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
		//							.setMessage("Contribution Added seccusfully")
		//							.setData(mapToContributionPanelresponce(panel)));
		//				}).orElseThrow(()->new UserNotFoundById("Invalid User Id"));
		//			}).orElseThrow(()-> new PanelNotFoundByIdException("Invalid panel Id"));
		//		}).get();
	}
	
//	public ResponseEntity<Responstructure<ContributionPanelResponse>> removeUserFromContribution(int userId,
//			int panelId) {
//
//		String email = SecurityContextHolder.getContext().getAuthentication().getName();
//		return userRepo.findByEmail(email).map(owner -> {
//			return panelRepo.findById(panelId).map(panel -> {
//				if (!blogRepo.existsByUserAndPanel(owner, panel))
//					throw new UNAUTHORIZEDException("Fiald To Add Contribute");
//				// throw new IllegalAccessRequestException("Fiald To Add Contribute");
//				return userRepo.findById(userId).map(contributer -> {
//					panel.getUsers().remove(contributer);
//					panelRepo.save(panel);
//					return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
//							.setMessage("contributer si added").setData(mapToResponse(panel)));
//				}).orElseThrow(() -> new UserNotFoundByIdException("user Not Found By Given Id"));
//			}).orElseThrow(() -> new PanelNotFoundByIdException("panel not found By GivenId"));
//		}).get();
//
//	}


	private ContributionPanelResponce mapToContributionPanelresponce(ContributionPanel panel) {
		// TODO Auto-generated method stub
		return ContributionPanelResponce.builder().panelId(panel.getPanelId()).users(panel.getUsers()).build();
	}

//	@Override
//	public ResponseEntity<ResponseStructure<ContributionPanelResponce>> removeUserFromContribution(int userId,
//			int panelId) {
//		
//		
//	}

}
