package com.example.cms.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cms.usermodel.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponce {
	
	private int blogId;
	private String title;
	private String[] topic;
	private String about;
    private User  user;
    
    private PublishResponce publishResponce;
}
