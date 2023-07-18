package com.hcc.controllers;

import com.hcc.dtos.AssignmentResponseDto;
import com.hcc.dtos.CareersResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.Career;
import com.hcc.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/careers")
public class CareerController {
    @Autowired
    private CareerService careerService;

    @GetMapping
    public List<CareersResponseDto> getCareers() {
        List<CareersResponseDto> careersResponseDtoArrayList = new ArrayList<>();
        List<Career> careerList = careerService.findAll();
        careerList.stream().forEach(career -> {
            careersResponseDtoArrayList.add(setDtoValue(Optional.of(career)));
        });

        return careersResponseDtoArrayList;
    }

    @GetMapping("{id}")
    public CareersResponseDto getCareerById(@PathVariable Long id) {
        return setDtoValue(careerService.findById(id));
    }

    private CareersResponseDto setDtoValue(Optional<Career> career) {
        CareersResponseDto careersResponseDto = new CareersResponseDto();
        careersResponseDto.setId(career.get().getId());
        careersResponseDto.setTitle(career.get().getTitle());
        careersResponseDto.setLocation(career.get().getLocation());
        careersResponseDto.setSalary(career.get().getSalary());
        return careersResponseDto;
    }
}
