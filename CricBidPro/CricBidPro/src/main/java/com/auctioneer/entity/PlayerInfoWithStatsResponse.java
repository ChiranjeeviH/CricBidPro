package com.auctioneer.entity;

import lombok.Data;

import java.util.List;

@Data
public class PlayerInfoWithStatsResponse {
    private PlayerEntity playerEntity;
    private List<BaseStatisticsEntity> statistics;
}
