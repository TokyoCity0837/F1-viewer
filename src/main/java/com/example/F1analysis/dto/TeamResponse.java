package com.example.f1analysis.dto;

public class TeamResponse {
    private int id;
    private String teamName;
    private String baseCountry;
    private String teamPrinciple;
    private int teamFoundation;
    private int teamChampionships;

    public TeamResponse(int id, String teamName, String baseCountry,
                        String teamPrinciple, int teamFoundation, int teamChampionships) {
        this.id = id;
        this.teamName = teamName;
        this.baseCountry = baseCountry;
        this.teamPrinciple = teamPrinciple;
        this.teamFoundation = teamFoundation;
        this.teamChampionships = teamChampionships;
    }

    public int getId() { return id; }
    public String getTeamName() { return teamName; }
    public String getBaseCountry() { return baseCountry; }
    public String getTeamPrinciple() { return teamPrinciple; }
    public int getTeamFoundation() { return teamFoundation; }
    public int getTeamChampionships() { return teamChampionships; }
}
