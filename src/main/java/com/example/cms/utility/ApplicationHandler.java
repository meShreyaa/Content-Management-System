package com.example.cms.utility;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.exception.BlogAlreadyExistsByTitle;
import com.example.cms.exception.BlogNotFoundById;
import com.example.cms.exception.EmailAlreadyExists;
import com.example.cms.exception.TitleAlreadyExists;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserNotFoundById;

@RestControllerAdvice
public class ApplicationHandler {
	
	private ErrorStructure<String> errorStructure;

	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String message, String rootCause){
		return new ResponseEntity<ErrorStructure<String>>(errorStructure.setStatusCode(status.value()).setMessage(message).setRootCause(rootCause),status);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistsByEmail(EmailAlreadyExists ex){
	return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already Exists with the given EMail");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> userNotFound(UserNotFoundById ex){
	return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already Exists with the given EMail");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> titleAlreadyExists(TitleAlreadyExists ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "this Title is Already Taken By Some User Give Another title");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> topicNotSpecifiedException(TopicNotSpecifiedException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Specify the topic");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> blogNotFoundById(BlogNotFoundById ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Blog does Not Exists");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> blogAlreadyExistsByTitle(BlogAlreadyExistsByTitle ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Blog Title Already Exists");
	}
}
