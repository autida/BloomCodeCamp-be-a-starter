package com.hcc.entities;


import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cohort_start_date")
    private LocalDate cohortStartDate;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Authority.class , cascade = CascadeType.ALL, mappedBy = "user")
//    @JoinColumn(name="user_id", referencedColumnName="id")
//    @OneToMany(targetEntity = Authority.class, mappedBy = "user")
    private List<Authority> authorities;

    public User(){};
    public User(LocalDate cohortStartDate, String username, String password) {
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCohortStartDate() {
        return cohortStartDate;
    }

    public void setCohortStartDate(LocalDate cohortStartDate) {
        this.cohortStartDate = cohortStartDate;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null || !Hibernate.isInitialized(authorities)) {
            // Fetch the authorities collection within an active Hibernate session
            Hibernate.initialize(authorities);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        authorities.forEach(authority -> roles.add(authority));
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
