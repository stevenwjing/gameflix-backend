package com.example.demo30.service;

import com.example.demo30.model.Course;
import com.example.demo30.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseServiceImplTest {
    @Autowired
    private CourseRepository repository;

    @Test
    void getAllCourses() {
        List<Course> items = repository.findAll();
        assertEquals(3, items.size());   // 3 courses in your DB
    }

    @Test
    public void testFindOne() {
        Course course = repository.findById(2L).get();   // your first course is id 2
        assertEquals("Systems Integration Intro", course.getCourseName());
    }
}