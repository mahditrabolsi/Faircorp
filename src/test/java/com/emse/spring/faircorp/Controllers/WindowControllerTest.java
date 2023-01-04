package com.emse.spring.faircorp.Controllers;


import com.emse.spring.faircorp.api.WindowController;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private static Logger logger = LogManager.getLogger(WindowControllerTest.class);
    @Test
    void shouldLoadWindows() throws Exception {
        logger.info("WindowControllerTest:shouldLoadWindows");
        Room room = new Room(1, "Room1");
        Window window1 = new Window("window 1", WindowStatus.OPEN, room);
        Window window2 = new Window("window 2", WindowStatus.CLOSED, room);
        given(windowDao.findAll()).willReturn(List.of(window1, window2));
        //Authorization with username= mahdi and password=user
        try {
            logger.debug("WindowControllerTest:shouldLoadWindows:try block");
            mockMvc.perform(get("/api/windows").header("Authorization", auth_header))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].name").value("window 1"))
                    .andExpect(jsonPath("$[1].name").value("window 2"));
        }
        catch (Exception e){
            logger.error("WindowControllerTest:shouldLoadWindows:Exception:"+e.getMessage());
        }
    }

    private Window createWindow(String name) {
        Room room = new Room(2, "Room1");
        return new Window(name, WindowStatus.OPEN, room);
    }

}
