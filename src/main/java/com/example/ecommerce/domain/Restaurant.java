package com.example.ecommerce.domain;

public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;

    public Restaurant(Long id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Restaurant{" + id + ", '" + name + '\'' + ", '" + address + '\'' +", '" + phoneNumber + '\'' + '}';
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getPhoneNumber() { return phoneNumber; }
}
