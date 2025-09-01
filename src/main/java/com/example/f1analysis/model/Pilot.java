package com.example.f1analysis.model;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "f1_pilots")

public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pilotId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Pilot(){};
    public Pilot(String firstName, String lastName, String nationality, LocalDate birthDate, Integer number, Team team){

        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.number = number;
        this.team = team;

    }

    public Integer getPilotId() { return pilotId; }
    public void setPilotId(Integer pilotId) { this.pilotId = pilotId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    
    public Integer getCarNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

}
