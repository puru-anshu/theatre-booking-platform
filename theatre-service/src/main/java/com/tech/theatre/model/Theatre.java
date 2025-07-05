package com.tech.theatre.model;

import jakarta.persistence.*;

@Entity
public class Theatre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;
    private int totalScreens;
    private int seatsPerScreen;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getTotalScreens() {
        return totalScreens;
    }
    public void setTotalScreens(int totalScreens) {
        this.totalScreens = totalScreens;
    }
    public int getSeatsPerScreen() {
        return seatsPerScreen;
    }
    public void setSeatsPerScreen(int seatsPerScreen) {
        this.seatsPerScreen = seatsPerScreen;
    }


}
