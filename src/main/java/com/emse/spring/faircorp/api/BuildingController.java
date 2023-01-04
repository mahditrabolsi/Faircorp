package com.emse.spring.faircorp.api;


import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.model.Building;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/buildings")
@Transactional
@CrossOrigin(origins = "*")
public class BuildingController {

    private final BuildingDao buildingDao;

    public BuildingController(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    private static Logger logger = LogManager.getLogger(BuildingController.class);
    @GetMapping
    public List<BuildingDto> findAll() {
        logger.info("BuildingController:findAll");

        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());

    }

    /*
    * This method is used to create a new building or update an existing building
    * @param dto
    * @return BuildingDto
    * */

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getName(),dto.getAddress()));
        } else {
            building = buildingDao.getReferenceById(dto.getId());
            building.setName(dto.getName());
            building.setAddress(dto.getAddress());
        }
        return new BuildingDto(building);
    }
    @GetMapping(path = "/{building_id}")
    public BuildingDto findById(@PathVariable Long building_id) {
        return buildingDao.findById(building_id).map(BuildingDto::new).orElse(null);
    }
    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id) {
        buildingDao.deleteById(building_id);
    }



}
