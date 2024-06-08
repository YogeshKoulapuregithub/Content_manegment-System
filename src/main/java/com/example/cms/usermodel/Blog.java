package com.example.cms.usermodel;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    private String title;
    private String[] topic;
    private String about;
    
    @ManyToOne
    private User user;
    
    @OneToOne
    private ContributionPanel contributionPanel;

}
