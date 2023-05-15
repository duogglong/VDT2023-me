package com.longnd.vn.service.impl;

import com.longnd.vn.dto.AttendeeDTO;
import com.longnd.vn.entity.AttendeeEntity;
import com.longnd.vn.repository.AttendeeRepository;
import com.longnd.vn.service.AttendeeService;
import com.sun.jdi.InternalException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<AttendeeDTO> getAll() {
        return attendeeRepository.findAllAttendees();
    }

    @Override
    public AttendeeDTO getById(Long id) {
        return new AttendeeDTO(attendeeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public AttendeeDTO save(AttendeeDTO dto) {
        // validate
        if (dto.getUsername() == null) {
            throw new InternalException();
        }

        AttendeeEntity entity;
        if (dto.getId() != null) {
            entity = attendeeRepository.findById(dto.getId()).orElseThrow(NullPointerException::new);
        } else {
            entity = new AttendeeEntity();
        }

        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setYearOfBirth(dto.getYearOfBirth());
        entity.setSex(dto.getSex());
        entity.setSchool(dto.getSchool());
        entity.setMajor(dto.getMajor());

        return new AttendeeDTO(attendeeRepository.save(entity));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (attendeeRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        }
        attendeeRepository.deleteById(id);
        return true;
    }
}
