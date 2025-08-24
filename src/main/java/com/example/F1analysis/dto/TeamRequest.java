package com.example.F1analysis.dto;

import jakarta.validation.constraints.*;

public class TeamRequest {

    private int id; 

    @NotBlank(message = "Название команды не может быть пустым")
    @Size(max = 50, message = "Название команды слишком длинное")
    private String teamName;

    @NotBlank(message = "Страна команды не может быть пустой")
    @Size(max = 50, message = "Название страны слишком длинное")
    private String baseCountry;

    @NotBlank(message = "Team Principle не может быть пустым")
    @Size(max = 50, message = "Имя Team Principle слишком длинное")
    private String teamPrinciple;

    @Min(value = 1800, message = "Год основания должен быть корректным")
    @Max(value = 2100, message = "Год основания слишком большой")
    private int teamFoundation;

    @Min(value = 0, message = "Количество чемпионств не может быть отрицательным")
    private int teamChampionships;

    public TeamRequest() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getBaseCountry() {
        return baseCountry;
    }

    public void setBaseCountry(String baseCountry) {
        this.baseCountry = baseCountry;
    }

    public String getTeamPrinciple() {
        return teamPrinciple;
    }

    public void setTeamPrinciple(String teamPrinciple) {
        this.teamPrinciple = teamPrinciple;
    }

    public int getTeamFoundation() {
        return teamFoundation;
    }

    public void setTeamFoundation(int teamFoundation) {
        this.teamFoundation = teamFoundation;
    }

    public int getTeamChampionships() {
        return teamChampionships;
    }

    public void setTeamChampionships(int teamChampionships) {
        this.teamChampionships = teamChampionships;
    }
}
