package com.zdata.registration.repository;

import com.zdata.registration.model.Course;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepository {
    private final Map<UUID, Course> courseMap = new HashMap<>();

    // Finds all courses
    public List<Course> findAll() {
        return new ArrayList<>(courseMap.values());
    }

    // Finds a course by its ID
    public Optional<Course> findById(UUID id) {
        return Optional.ofNullable(courseMap.get(id));
    }

    // Finds a course by its code
    public Optional<Course> findByCode(String code) {
        return courseMap.values().stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    // Saves a course, either creating or updating
    public Course save(Course course) {
        courseMap.put(course.getId(), course);
        return course;
    }

    // Deletes a course by its ID
    public void delete(UUID id) {
        courseMap.remove(id);
    }

}
