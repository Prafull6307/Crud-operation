package com.javatpoint.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javatpoint.model.student;

public interface studentRepository extends CrudRepository<student,Long> {
	 List<student> findByName(String name); 
}
