package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name = "Heater")
public class Heater {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;


    private Long power;

    @ManyToOne(optional = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HeaterStatus heaterStatus;


    public Heater() {}

    public Heater(String name, Room room,Long power ,HeaterStatus heaterStatus) {
        this.name = name;
        this.room = room;
        this.power = power;
        this.heaterStatus = heaterStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }
}
