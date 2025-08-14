package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamRequest {

    private int id;

    @NotBlank(message = "Название команды не может быть пустым")
    @Size(max = 50, message = "Название команды слишком длинное")
    private String name;

    @NotBlank(message = "Страна команды не может быть пустой")
    @Size(max = 50, message = "Название страны слишком длинное")
    private String country;

    public TeamRequest() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
