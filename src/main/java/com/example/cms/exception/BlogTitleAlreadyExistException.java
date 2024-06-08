package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlogTitleAlreadyExistException extends RuntimeException {

	String message;
	

}
