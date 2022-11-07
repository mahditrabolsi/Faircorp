package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;
    private final BuildingDao buildingDao;

    public RoomController(RoomDao roomDao,BuildingDao buildingDao) {
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
    }
    //get all rooms
    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }
    //add or update a room
    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        Building building= buildingDao.getReferenceById(dto.getBuildingId());
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(),dto.getName(),dto.getCurrentTemperature(),dto.getTargetTemperature(),building));
        } else {
            room = roomDao.getReferenceById(dto.getId());
            room.setFloor(dto.getFloor());
            room.setName(dto.getName());
            room.setCurrent_temperature(dto.getCurrentTemperature());
            room.setTarget_temperature(dto.getTargetTemperature());
        }
        return new RoomDto(room);
    }
    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null);
    }
    @GetMapping(path = "/building/{building_id}")
    public List<RoomDto> findByBuildingId(@PathVariable Long building_id) {
        return roomDao.findByBuildingId(building_id).stream().map(RoomDto::new).collect(Collectors.toList());
    }
    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        roomDao.deleteById(room_id);
    }

    //switch the room windows
    @PutMapping(path = "/{room_id}/switchWindows")
    public RoomDto switchWindows(@PathVariable Long room_id) {
        Room room = roomDao.getOne(room_id);
        room.getWindows().forEach(window ->window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN));
        return new RoomDto(room);
    }

    //switch the room heaters
    @PutMapping(path = "/{room_id}/switchHeaters")
    public RoomDto switchHeaters(@PathVariable Long room_id) {
        Room room = roomDao.getReferenceById(room_id);
        room.getHeaters().forEach(heater ->heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON));
        return new RoomDto(room);
    }

}
