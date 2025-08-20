package com.example.F1analysis.model;


import jakarta.persistence.*;

@Entity
@Table(name = "f1_pilots")

public class f1_pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pilotId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality;

    @Column(name = "birth_date")
    private java.sql.Date birthDate;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private f1_team team;

    public f1_pilot(){};
    public f1_pilot(String firstName, String lastName, String nationality, java.sql.Date birthDate, Integer number, f1_team team){

        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.number = number;
        this.team = team;

    }

    public Long getPilotId() { return pilotId; }
    public void setPilotId(Long pilotId) { this.pilotId = pilotId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public java.sql.Date getBirthDate() { return birthDate; }
    public void setBirthDate(java.sql.Date birthDate) { this.birthDate = birthDate; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public f1_team getTeam() { return team; }
    public void setTeam(f1_team team) { this.team = team; }

}
