package com.emse.spring.faircorp.model;


import javax.persistence.*;

@Entity
@Table(name = "RWindow")
public class Window {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WindowStatus windowStatus;

    @ManyToOne
    private Room room;

    public Window(){

    }

    public Window(String name, WindowStatus windowStatus,Room room) {
        this.name = name;
        this.windowStatus = windowStatus;
        this.room=room;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }
}
