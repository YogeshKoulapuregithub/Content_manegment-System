package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class UserNotFoundById extends RuntimeException {
	private String message;

}
