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





    @Test
    void shouldSwitchWindow() throws Exception {
        Window expectedWindow = createWindow("Window 1");
        Assertions.assertThat(expectedWindow.getWindowStatus()).isEqualTo(WindowStatus.OPEN);
        given(windowDao.findById(-10L)).willReturn(Optional.of(expectedWindow));
        mockMvc.perform(post("/api/windows/switch/-10").header("Authorization", auth_header))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Window 1"))
                .andExpect(jsonPath("$.windowStatus").value("CLOSED"));
    }

    @Test
    void shouldUpdateWindow() throws Exception {
        Window expectedWindow = createWindow("window 1");
        expectedWindow.setId(1L);
        String json = objectMapper.writeValueAsString(new WindowDto(expectedWindow));

        given(roomDao.getReferenceById(anyLong())).willReturn(expectedWindow.getRoom());
        given(windowDao.getReferenceById(anyLong())).willReturn(expectedWindow);
        given(windowDao.save(any(Window.class))).willReturn(expectedWindow);

        mockMvc.perform(post("/api/windows").header("Authorization", auth_header).contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("window 1"))
                .andExpect(jsonPath("$.windowStatus").value("OPEN"));
    }

    @Test
    void shouldCreateWindow() throws Exception {
        Window expectedWindow = createWindow("window 1");
        expectedWindow.setId(null);
        String json = objectMapper.writeValueAsString(new WindowDto(expectedWindow));

        given(roomDao.getReferenceById(anyLong())).willReturn(expectedWindow.getRoom());
        given(windowDao.save(any())).willReturn(expectedWindow);

        mockMvc.perform(post("/api/windows").header("Authorization", auth_header).content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("window 1"));
    }

    @Test
    void shouldDeleteWindow() throws Exception {
        mockMvc.perform(delete("/api/windows/-3").header("Authorization", auth_header))
                .andExpect(status().isOk());

    }

    private Window createWindow(String name) {
        Room room = new Room(2, "Room1");
        return new Window(name, WindowStatus.OPEN, room);
    }

}
