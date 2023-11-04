package com.auctioneer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Player{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", playingRole='" + playingRole + '\'' +
                ", playerSkill='" + playerSkill + '\'' +
                '}';
    }

    private long userId;
    private String name;
    private String city;
    private String playingRole;
    private String playerSkill;
}
