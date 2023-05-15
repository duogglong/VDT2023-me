package com.longnd.vn.controller;

import com.longnd.vn.dto.AttendeeDTO;
import com.longnd.vn.service.AttendeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/attendees")
@CrossOrigin("*")
@Slf4j
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;
//    private final AttendeeService attendeeService;

//    public AttendeeController(AttendeeService attendeeService) {
//        this.attendeeService = attendeeService;
//    }

    @GetMapping("/all")
    public List<AttendeeDTO> getAll() {
        log.info("Call getAll()");
        return attendeeService.getAll();
    }

    @GetMapping("/{id}")
    public AttendeeDTO getById(@PathVariable("id") Long id) {
        log.info("Call getById()");
        return attendeeService.getById(id);
    }

    @PutMapping
    public AttendeeDTO update(@RequestBody AttendeeDTO attendee) {
        log.info("Call update()");
        return attendeeService.save(attendee);
    }

    @PostMapping
    public AttendeeDTO create(@RequestBody AttendeeDTO attendee) {
        log.info("Call create()");
        return attendeeService.save(attendee);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable("id") Long id) {
        log.info("Call deleteById()");
        return attendeeService.deleteById(id);
    }
}
