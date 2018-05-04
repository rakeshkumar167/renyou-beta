package com.renyou.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.renyou.db.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer>{
	List<Project> findByPromoted(Boolean promoted);
}
