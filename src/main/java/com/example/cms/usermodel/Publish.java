package com.example.cms.usermodel;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Publish {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int publishId;
	@NotBlank
	@NotNull
	private String seoTitle;
	@Size(max = 150,message = "charecter size has to less than 4000")
	private String seoDescription;
	private String[] seoTopics;
	
	@CreatedDate
	private LocalDateTime createdAt;
	@OneToOne
	private BlogPost blogPost;
	@OneToOne
	private Schedule schedule;

}
