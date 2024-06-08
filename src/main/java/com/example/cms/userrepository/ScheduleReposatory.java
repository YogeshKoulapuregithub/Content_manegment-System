package com.example.cms.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.usermodel.Schedule;

public interface ScheduleReposatory extends JpaRepository<Schedule,Integer> {

}
