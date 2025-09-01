package com.example.F1analysis.dto;

import java.time.LocalDate;

public class PilotResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate birthDate;
    private Integer carNumber;
    private String teamName;

    public PilotResponse(Integer id, String firstName, String lastName, String nationality,
                         LocalDate birthDate, Integer carNumber, String teamName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.carNumber = carNumber;
        this.teamName = teamName;
    }

    public Integer getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNationality() { return nationality; }
    public LocalDate getBirthDate() { return birthDate; }
    public Integer getCarNumber() { return carNumber; }
    public String getTeamName() { return teamName; }
}
