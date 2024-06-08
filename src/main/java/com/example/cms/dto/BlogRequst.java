	package com.example.cms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequst {
	//@NotBlank(message = "Tittle is Required")
//	@Pattern(regexp = "^[a-z A-Z]+$")
	private String title;
	private String[] topics;
	private String about;

}
