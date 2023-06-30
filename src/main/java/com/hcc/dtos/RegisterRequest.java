package com.hcc.dtos;

import java.time.LocalDate;
import java.util.Date;

public class RegisterRequest {

    private LocalDate cohortStartDate;
    private String password;
    private String username;



    public RegisterRequest(LocalDate cohortStartDate, String password, String username) {

        this.cohortStartDate = cohortStartDate;
        this.password = password;
        this.username = username;
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
}
