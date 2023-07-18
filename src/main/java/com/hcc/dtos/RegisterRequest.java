package com.hcc.dtos;

import java.time.LocalDate;
import java.util.Date;

public class RegisterRequest {

    private LocalDate cohortStartDate;
    private String password;
    private String username;
    private String role;



    public RegisterRequest(LocalDate cohortStartDate, String password, String username, String role) {

        this.cohortStartDate = cohortStartDate;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public LocalDate getCohortStartDate() {
        return cohortStartDate;
    }

    public void setCohortStartDate(LocalDate cohortStartDate) {
        this.cohortStartDate = cohortStartDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "cohortStartDate=" + cohortStartDate +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
