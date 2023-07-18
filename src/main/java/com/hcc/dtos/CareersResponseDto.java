package com.hcc.dtos;

public class CareersResponseDto {
    private int id;
    private String title;
    private double salary;
    private String location;

    // Constructors (optional)
    public CareersResponseDto() {
    }

    public CareersResponseDto(int id, String title, double salary, String location) {
        this.id = id;
        this.title = title;
        this.salary = salary;
        this.location = location;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
