package com.example.cms.dto;

import java.util.List;

import com.example.cms.usermodel.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContributionPanelResponce {
	
	private int panelId;
	private List<User> users;

}
