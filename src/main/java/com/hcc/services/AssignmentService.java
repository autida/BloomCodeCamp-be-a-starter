package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.exceptions.CreateAssignmentException;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;


    public Assignment saveAssignment(Assignment assignment) throws CreateAssignmentException {
        Assignment newAssignment = assignmentRepository.saveAndFlush(assignment);
        return newAssignment;
    }


    public Optional<Assignment> findById(Long id) {
        return assignmentRepository.findById(id);
    }

        public List<Assignment> findByUser(Long id) {
            return assignmentRepository.findByUserId(id);
    }

}
