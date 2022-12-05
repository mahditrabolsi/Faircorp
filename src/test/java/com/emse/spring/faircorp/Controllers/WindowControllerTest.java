package com.emse.spring.faircorp.Controllers;


import com.emse.spring.faircorp.api.WindowController;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WindowController.class)
class WindowControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WindowDao windowDao;

    @MockBean
    private RoomDao roomDao;

    String auth_header="Basic "+ Base64.encodeBase64String("mahdi:user".getBytes());

    @Test
    void shouldLoadWindows() throws Exception {
        Room room = new Room(1, "Room1");
        Window window1 = new Window("window 1", WindowStatus.OPEN, room);
        Window window2 = new Window("window 2", WindowStatus.CLOSED, room);
        given(windowDao.findAll()).willReturn(List.of(window1, window2));
        //Authorization with username= mahdi and password=user
        mockMvc.perform(get("/api/windows").header("Authorization", auth_header))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("window 1"))
                .andExpect(jsonPath("$[1].name").value("window 2"));
    }

    private Window createWindow(String name) {
        Room room = new Room(2, "Room1");
        return new Window(name, WindowStatus.OPEN, room);
    }

}
