package com.example.cms.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IllegalAccessReequestException extends RuntimeException {

	private String message;
}
