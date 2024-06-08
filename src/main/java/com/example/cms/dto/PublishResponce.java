package com.example.cms.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishResponce {
	private int publishId;
	private String seoTitle;
	private String seoDescription;
	private String[] seoTopics;
	
	private LocalDateTime createdAt;

}
