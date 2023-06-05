package com.hcc.entities;

import java.util.Objects;

public class Authority {
    private Long id;
    private String authority;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority authority1)) return false;
        return id.equals(authority1.id) && Objects.equals(authority, authority1.authority) && Objects.equals(user, authority1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority, user);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", user=" + user +
                '}';
    }
}
