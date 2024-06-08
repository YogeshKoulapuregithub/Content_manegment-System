package com.example.cms.dto;

import org.hibernate.validator.constraints.Length;

import com.example.cms.enums.PostType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostRequst {
    @NotNull(message = "title should not be null") 
	private String title;
	private String subTitle;
	@Length(min = 5,message = "summary should be atlest 500 Charecters")
	private String summary;
	
}
