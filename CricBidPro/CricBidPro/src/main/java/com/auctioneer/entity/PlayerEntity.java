package com.auctioneer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Player id mandatory")
    private long playerId;
    private String name;
    private String city;
    private String playingRole;
    private String playerSkill;

}
