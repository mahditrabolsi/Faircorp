package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;

import javax.persistence.EntityManager;

public class RoomDaoCustomImpl implements RoomDaoCustom {

    private EntityManager em;
    @Override
    public Room findRoomByName(String name) {
        String jpql = "select r from Room r where r.name = :name";
        return em.createQuery(jpql, Room.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
