package com.hcc.services;

import com.hcc.entities.Authority;
import com.hcc.entities.Career;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerService {
    @Autowired
    CareerRepository careerRepository;

    public List<Career> findAll() {
        return careerRepository.findAll();
    }
    public Optional<Career> findById(Long id) {
        return careerRepository.findById(id);
    }
}
