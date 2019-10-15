package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.dev.entity.Student;
import com.example.dev.repository.StudentRepository;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DemoApplication {

	@Autowired
	DataSource dataSource;

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Transactional(readOnly = true)
	public void run(String... args) throws Exception {

		System.out.println("DATASOURCE = " + dataSource);

		System.out.println("\n1.findAll()...");
		for (Student student : studentRepository.findAll()) {
			System.out.println(student);
		}
	}

}
