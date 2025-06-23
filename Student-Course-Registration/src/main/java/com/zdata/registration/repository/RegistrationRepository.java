package com.zdata.registration.repository;

import com.zdata.registration.model.Registration;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RegistrationRepository {
    private final List<Registration> registrations = new ArrayList<>();

    // method to save a registration
    public void save(Registration registration) {
        registrations.add(registration);
    }

    // method to delete a registration by studentId and courseId
    public void delete(Integer studentId, Integer courseId) {
        registrations.removeIf(r ->
                r.getStudentId().equals(studentId) &&
                        r.getCourseId().equals(courseId));
    }

    //method to check whether a specific student is already registered for a specific course
    public boolean exists(Integer studentId, Integer courseId) {
        return registrations.stream().anyMatch(r ->
                r.getStudentId().equals(studentId) &&
                        r.getCourseId().equals(courseId));
    }

    // method to find all courses registered by a specific student
    public List<Integer> findCoursesByStudentId(Integer studentId) {
        return registrations.stream()
                .filter(r -> r.getStudentId().equals(studentId))
                .map(Registration::getCourseId)
                .collect(Collectors.toList());
    }
}
