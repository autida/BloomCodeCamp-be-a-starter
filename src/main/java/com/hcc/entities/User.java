package com.hcc.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private Date cohortStartDate;
    private String username;
    private String password;
    private List<Authority> authorities;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cohortStartDate=" + cohortStartDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
