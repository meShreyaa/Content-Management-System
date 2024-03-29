package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TitleAlreadyExists extends RuntimeException{

	private String message;
}
