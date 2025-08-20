package com.example.F1analysis.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

public class DriverRequest {

    @Positive(message = "ID должен быть положительным числом")
    private int id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 50, message = "Имя слишком длинное")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(max = 50, message = "Фамилия слишком длинная")
    private String lastName;

    @NotBlank(message = "Страна не может быть пустой")
    @Size(max = 50, message = "Название страны слишком длинное")
    private String country;

    @NotNull(message = "Дата рождения обязательна")
    private LocalDate birthDate;

    @Min(value = 1, message = "Номер машины должен быть больше 0")
    @Max(value = 99, message = "Номер машины не может быть больше 99")
    private int carNumber;

    @Positive(message = "ID команды должен быть положительным числом")
    private int teamId;

    public DriverRequest() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Дата рождения обязательна");
        }
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 15) {
            throw new IllegalArgumentException("Пилот должен быть не младше 15 лет");
        }
        this.birthDate = birthDate;
    }


    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}