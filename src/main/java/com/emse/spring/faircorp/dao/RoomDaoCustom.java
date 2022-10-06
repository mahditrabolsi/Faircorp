package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;

public interface RoomDaoCustom {
   // method to find all windows by room name
    Room findRoomByName(String name);
}
