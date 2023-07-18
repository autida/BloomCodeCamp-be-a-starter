package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.Authority;
import com.hcc.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public List<Authority> findByUser(Long id) {
        return authorityRepository.findByUserId(id);
    }
}
