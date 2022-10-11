package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;

import java.util.List;

public interface HeaterDaoCustom {

    void deleteAllHeatersByRoom(Long id);
    List<Heater> findRoomOpenHeaters(Long id);
}
