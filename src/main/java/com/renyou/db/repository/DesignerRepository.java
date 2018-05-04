package com.renyou.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.renyou.db.Designer;

public interface DesignerRepository extends CrudRepository<Designer, Integer>{
	List<Designer> findByPromoted(Boolean promoted);
}
