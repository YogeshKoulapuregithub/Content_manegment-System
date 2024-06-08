package com.example.cms.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PanelNotFoundByIdException extends RuntimeException{

	private String message;
}
