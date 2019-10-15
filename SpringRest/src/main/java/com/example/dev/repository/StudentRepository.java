package com.example.dev.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.dev.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	public Optional<Student> findById(Long id);

}
