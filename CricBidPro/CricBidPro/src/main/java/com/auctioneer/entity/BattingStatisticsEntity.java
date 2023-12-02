package com.auctioneer.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "batting_statistics")
@Data
public class BattingStatisticsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String value;
    private int isUserProperty;

    // Other fields and relationships as needed
}