package com.example.cms.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequest {

	@Column(unique = true)
	@NotNull
	private String title;
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabets are allowed")
	@NotNull
	private String topics;
	private String about;
	
	public String[] getTopics() {
	    return topics.split(",");
	}
}
