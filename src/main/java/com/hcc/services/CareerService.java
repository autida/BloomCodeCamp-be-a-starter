package com.hcc.services;

import com.hcc.entities.Authority;
import com.hcc.entities.Career;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CareerService {
    @Autowired
    CareerRepository careerRepository;

    public List<Career> findAll() {
        return careerRepository.findAll();
    }
}
