package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter

@AllArgsConstructor
public class UserAlreadyExistByEmailException extends RuntimeException {
private String messsage;

}
