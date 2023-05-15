package com.longnd.vn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.longnd.vn.dto.AttendeeDTO;
import com.longnd.vn.entity.AttendeeEntity;
import com.longnd.vn.service.AttendeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AttendeeController.class)
public class AttendeeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AttendeeService attendeeService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAll() throws Exception {
        // Arrange
        List<AttendeeDTO> attendeeDTOList = new ArrayList<>();
        attendeeDTOList.add(new AttendeeDTO(1L, "user1", "John Doe", 1990L, "Male", "School A", "Major A"));
        attendeeDTOList.add(new AttendeeDTO(2L, "user2", "Jane Smith", 1992L, "Female", "School B", "Major B"));

        when(attendeeService.getAll()).thenReturn(attendeeDTOList);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/attendees/all"));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("user1")))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[0].yearOfBirth", is(1990)))
                .andExpect(jsonPath("$[0].sex", is("Male")))
                .andExpect(jsonPath("$[0].school", is("School A")))
                .andExpect(jsonPath("$[0].major", is("Major A")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("user2")))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")))
                .andExpect(jsonPath("$[1].yearOfBirth", is(1992)))
                .andExpect(jsonPath("$[1].sex", is("Female")))
                .andExpect(jsonPath("$[1].school", is("School B")))
                .andExpect(jsonPath("$[1].major", is("Major B")));

        verify(attendeeService, times(1)).getAll();
        verifyNoMoreInteractions(attendeeService);
    }

    @Test
    public void testGetAttendeeById() throws Exception {
        Long attendeeId = 1L;
        AttendeeDTO dto = new AttendeeDTO();
        dto.setId(attendeeId);
        dto.setName("John Doe");

        // Giả lập dữ liệu trả về từ service
        Mockito.when(attendeeService.getById(attendeeId)).thenReturn(dto);

        // Gửi yêu cầu GET /api/attendees/{id}
        mockMvc.perform(get("/api/attendees/{id}", attendeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(attendeeId))
                .andExpect(jsonPath("$.name").value("John Doe"));

        // Kiểm tra phương thức getById đã được gọi đúng số lần và với đúng đối số
        verify(attendeeService, times(1)).getById(attendeeId);
        verifyNoMoreInteractions(attendeeService);
    }

    @Test
    public void testCreateAttendee() throws Exception {
        AttendeeDTO attendeeDTO = new AttendeeDTO();
        attendeeDTO.setName("John Doe");

        // Giả lập dữ liệu trả về từ service
//        Mockito.when(attendeeService.save(any(AttendeeDTO.class))).thenReturn(attendeeDTO);
//        Mockito.when(attendeeService.save(attendeeDTO)).thenReturn(attendeeDTO);
        Mockito.when(attendeeService.save(any(AttendeeDTO.class)));

        // Gửi yêu cầu POST /api/attendees
        // Gửi yêu cầu POST /api/attendees
//        mockMvc.perform(post("/api/attendees")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(attendeeDTO)))
//                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/attendees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attendeeDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"));

        // Kiểm tra phương thức save đã được gọi đúng số lần và với đúng đối số
//        verify(attendeeService, times(1)).save(any(AttendeeDTO.class));
//        verifyNoMoreInteractions(attendeeService);
    }

}