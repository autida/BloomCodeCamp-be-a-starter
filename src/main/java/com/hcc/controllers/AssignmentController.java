package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
//need an assignment Service
    //service is for business logic
    // need an userServiceImpl
    //Port Mapping
//    @GetMapping
//    public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user) {
//        Set<Assignment> assignmentsByUser = assignmentService.findByUser(user);
//
//        return ResponseEntity.ok(assignmentsByUser);
//    }
//
//    @DeleteMapping ("{assignmentId}")

}
