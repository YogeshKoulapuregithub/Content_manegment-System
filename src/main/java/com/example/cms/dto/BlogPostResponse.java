package com.example.cms.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.cms.enums.PostType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogPostResponse 
{
	private int postId;
	private String title;
	private String subTitle;
	private String summary;
	private PostType postType;
	
	@CreatedDate
	private LocalDateTime createAt;
	
	@CreatedBy
	private String createBy;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;
	
	@LastModifiedBy
	private String lastModifiedBy;
}
