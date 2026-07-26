package com.example.demo30.service;

import com.example.demo30.model.Student;
import com.example.demo30.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentServiceImplTest {
    @Autowired
    private StudentRepository repository;

    @Test
    void getAllStudents() {
        List<Student> items = repository.findAll();
        assertEquals(2, items.size());   // 2 students in your DB
    }

    @Test
    public void testFindOne() {
        Student student = repository.findById(1L).get();   // student sid 1
        assertEquals("David Peslak", student.getStudName());
    }
}