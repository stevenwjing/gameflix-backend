package com.example.demo30.repository;

import com.example.demo30.model.Course;
import com.example.demo30.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Optional<Student> findByStudentId(Long sid);
}