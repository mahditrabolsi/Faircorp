package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class HeaterDaoTest {
    //test HeaterDao

    @Autowired
    private HeaterDao heaterDao;
    private static Logger logger = LogManager.getLogger(HeaterDaoTest.class);


    @Test
    public void shouldFindAHeater() {
        Heater heater = heaterDao.getReferenceById(-10L);
        Assertions.assertThat(heater.getName()).isEqualTo("Heater 1");
        Assertions.assertThat(heater.getHeaterStatus()).isEqualTo(HeaterStatus.ON);
        logger.info("HeaterDaoTest:shouldFindAHeater");
        logger.info("HeaterDaoTest:shouldFindAHeater:heater.getName():"+heater.getName());

    }
}
