package com.hcc.controllers;

import com.hcc.dtos.AssignmentResponseDto;
import com.hcc.dtos.CareersResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;
    @GetMapping
    public List<AssignmentResponseDto> getAssignmentsByUser(@AuthenticationPrincipal User user) {
        List<AssignmentResponseDto> assignmentResponseDtoList = new ArrayList<>();
        List<Assignment> assignmentsByUser = assignmentService.findByUser(user.getId());
        assignmentsByUser.stream().forEach(assignment -> {
            assignmentResponseDtoList.add(setDtoValue(Optional.of(assignment)));
        });

        return assignmentResponseDtoList;
    }


    @PostMapping
    public AssignmentResponseDto saveAssignments(@RequestBody Assignment assignment,
                                                 @AuthenticationPrincipal User user) throws Exception {
        try {
            assignment.setUser(user);
            Assignment newAssignment = assignmentService.saveAssignment(assignment);
           return setDtoValue(Optional.of(newAssignment));
        } catch (Exception e) {
            throw new Exception("Something went wrong.", e);
        }
    }




    @GetMapping("{id}")
    public AssignmentResponseDto getAssignmentsById(@PathVariable Long id,
                                                    @AuthenticationPrincipal User user) throws Exception {
        if(!user.isCredentialsNonExpired()) {
            throw new Exception("Invalid user");
        }
        try {
            Optional<Assignment> assignment = assignmentService.findById(id);
            return setDtoValue(assignment);
        } catch (Exception e) {
            throw new Exception("Something went wrong.", e);
        }
    }

    @PutMapping("{id}")
    public AssignmentResponseDto updateAssignment(@PathVariable Long id,
                                              @RequestBody Assignment updateAssignment,
                                              @AuthenticationPrincipal User user) throws Exception {
        try {
            Optional<Assignment> assignment = assignmentService.findById(id);

            assignment.get().setBranch(updateAssignment.getBranch());
            assignment.get().setGithubUrl(updateAssignment.getGithubUrl());
            assignment.get().setNumber(updateAssignment.getNumber());
            assignment.get().setReviewVideoUrl(updateAssignment.getReviewVideoUrl());
            assignment.get().setStatus(updateAssignment.getStatus());

            //Create new Assignment
            return saveAssignments(assignment.get(), user);
        } catch (Exception e) {
            throw new Exception("Update assignment error.",e);
        }

    }

    private AssignmentResponseDto setDtoValue(Optional<Assignment> assignment) {
        AssignmentResponseDto assignmentResponseDto = new AssignmentResponseDto();
        assignmentResponseDto.setId(assignment.get().getId());
        assignmentResponseDto.setBranch(assignment.get().getBranch());
        assignmentResponseDto.setNumber(assignment.get().getNumber());
        assignmentResponseDto.setGithubUrl(assignment.get().getGithubUrl());
        assignmentResponseDto.setReviewVideoUrl(assignment.get().getReviewVideoUrl());
        assignmentResponseDto.setStatus(assignment.get().getStatus());
        if(assignment.get().getCodeReviewer() != null) {
            assignmentResponseDto.setCodeReviewerId(assignment.get().getCodeReviewer().getId());
        }
        if(assignment.get().getUser() != null) {
            assignmentResponseDto.setUserId(assignment.get().getUser().getId());
        }
        return assignmentResponseDto;
    }


}
