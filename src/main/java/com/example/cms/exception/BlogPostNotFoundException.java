package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlogPostNotFoundException extends RuntimeException 
{
	private String mesage;

}
