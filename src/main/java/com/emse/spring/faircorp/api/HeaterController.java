package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {


    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }
    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }
    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {
        Room room= roomDao.getReferenceById(dto.getRoomId());
        Heater heater = null;
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getName(),room,dto.getHeaterStatus()));
        } else {
            heater = heaterDao.getReferenceById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
            heater.setName(dto.getName());
            heater.setRoom(room);
            heater.setPower(dto.getPower());
        }
        return new HeaterDto(heater);
    }

    @GetMapping(path = "/room/{room_id}")
    public List<HeaterDto> findHeatersByRoomId(@PathVariable Long room_id) {
        return heaterDao.findHeaterByRoomId(room_id).stream().map(HeaterDto::new).collect(Collectors.toList());
    }
    @PostMapping(path = "/switch/{heater_id}")
    public HeaterDto switchStatus(@PathVariable Long heater_id) {
        Heater heater = heaterDao.getReferenceById(heater_id);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF : HeaterStatus.ON);
        return new HeaterDto(heater);
    }
    @DeleteMapping(path = "/{heater_id}")
    public void delete(@PathVariable Long heater_id) {
        heaterDao.deleteById(heater_id);
    }
}
