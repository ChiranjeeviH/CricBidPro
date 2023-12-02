package com.auctioneer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Player{" +
                "userId=" + playerId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", playingRole='" + playingRole + '\'' +
                ", playerSkill='" + playerSkill + '\'' +
                '}';
    }

    private long playerId;
    private String name;
    private String city;
    private String playingRole;
    private String playerSkill;

  /*  @OneToOne(mappedBy = "player")
    private BattingStatisticsEntity battingStatistics;

    @OneToOne(mappedBy = "player")
    private BowlingStatisticsEntity bowlingStatistics;

    @OneToOne(mappedBy = "player")
    private FieldingStatisticsEntity fieldingStatistics;

    @OneToOne(mappedBy = "player")
    private CaptainStatisticsEntity captainStatistics;*/
}
