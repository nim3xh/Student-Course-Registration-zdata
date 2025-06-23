package com.zdata.registration.dto;


public class CourseResponseDTO {
    private Integer id;
    private String code;
    private String title;
    private String instructor;

    // Constructor
    public CourseResponseDTO(Integer id, String code, String title, String instructor) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.instructor = instructor;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
