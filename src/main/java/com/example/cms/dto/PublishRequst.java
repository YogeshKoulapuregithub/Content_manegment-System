package com.example.cms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PublishRequst {
	
	private String seoTitle;
	private String seoDescription;
	private String[] seoTopics;
	
	private ScheduleRequst schedule;

}
