package com.longnd.vn.controller;

import com.longnd.vn.entity.AttendeeEntity;
import com.longnd.vn.repository.AttendeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/attendees")
@CrossOrigin("*")
@Slf4j
public class AttendeeController {
    private final AttendeeRepository attendeeRepository;

    public AttendeeController(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @GetMapping("/all")
    public List<AttendeeEntity> getAll() {
        log.info("Call getAll");
        List<AttendeeEntity> result = attendeeRepository.findAll();
        return result;
    }
}
