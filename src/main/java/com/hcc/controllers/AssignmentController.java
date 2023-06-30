package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @GetMapping
    public List<Assignment> getAssignmentsByUser(@AuthenticationPrincipal User user) {
        List<Assignment> assignmentsByUser = assignmentService.findByUser(user.getId());

//        return ResponseEntity.ok().body(assignmentsByUser);
        System.out.println(assignmentsByUser);
        return assignmentsByUser;
    }


    @PostMapping
    public ResponseEntity<?> saveAssignments(@RequestBody Assignment assignment,
                                            @AuthenticationPrincipal User user) {
        assignment.setUser(user);
        Assignment newAssignment = assignmentService.saveAssignment(assignment);
        return ResponseEntity.ok().body(newAssignment);
    }


    @GetMapping("{id}")
    public Optional<Assignment> getAssignmentsById(@PathVariable Long id,
                                                   @AuthenticationPrincipal User user) {
        return assignmentService.findById(id);

//        return ResponseEntity.ok().body(assignmentsByUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable Long id,
                                              @RequestBody Assignment updateAssignment,
                                              @AuthenticationPrincipal User user) throws Exception {
        Optional<Assignment> assignmentOpt = getAssignmentsById(id, user);
        assignmentOpt.orElseThrow(() -> new Exception("Assignment is not found."));

        assignmentOpt.get().setBranch(updateAssignment.getBranch());
        assignmentOpt.get().setGithubUrl(updateAssignment.getGithubUrl());
        assignmentOpt.get().setNumber(updateAssignment.getNumber());
        assignmentOpt.get().setUser(user);
        assignmentOpt.get().setReviewVideoUrl(updateAssignment.getReviewVideoUrl());
        assignmentOpt.get().setStatus(updateAssignment.getStatus());
        return saveAssignments(assignmentOpt.get(), user);
    }
}
