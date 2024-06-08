package com.example.cms.usermodel;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.cms.enums.PostType;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne
	private Blog blog;
	
	@OneToOne
	private Publish publish;

}
