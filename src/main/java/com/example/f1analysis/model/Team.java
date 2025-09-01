package com.example.f1analysis.model;
import jakarta.persistence.*;

@Entity
@Table(name = "f1_teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "base_country", nullable = false)
    private String baseCountry;

    @Column(name = "team_principle", nullable = false)
    private String teamPrinciple;

    @Column(name = "team_foundation")
    private int teamFoundation;

    @Column(name = "team_championships", columnDefinition = "int default 0")
    private int teamChampionships;

    public Team() {}


    public Team(String teamName, String baseCountry, String teamPrinciple, int teamFoundation, int teamChampionships) {
        this.teamName = teamName;
        this.baseCountry = baseCountry;
        this.teamPrinciple = teamPrinciple;
        this.teamFoundation = teamFoundation;
        this.teamChampionships = teamChampionships;
    }

  
    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getCountry() { return baseCountry; }
    public void setCountry(String baseCountry) { this.baseCountry = baseCountry; }

    public String getTeamPrinciple() { return teamPrinciple; }
    public void setTeamPrinciple(String teamPrinciple) { this.teamPrinciple = teamPrinciple; }

    public int getTeamFoundation() { return teamFoundation; }
    public void setTeamFoundation(int teamFoundation) { this.teamFoundation = teamFoundation; }

    public int getTeamChampionships() { return teamChampionships; }
    public void setTeamChampionships(int teamChampionships) { this.teamChampionships = teamChampionships; }
}
