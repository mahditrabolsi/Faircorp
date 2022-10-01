package com.emse.spring.faircorp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HeaterDaoCustomImpl implements HeaterDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void deleteAllHeatersByRoom(Long id) {
        String jpql = "delete from Heater h where h.room.id = :id";
        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }
}
