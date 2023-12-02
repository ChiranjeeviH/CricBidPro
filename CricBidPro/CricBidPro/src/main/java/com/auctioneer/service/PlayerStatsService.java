package com.auctioneer.service;

import com.auctioneer.entity.*;
import com.auctioneer.json.PlayerStats;
import com.auctioneer.repository.BattingRepository;
import com.auctioneer.repository.BowlingRepository;
import com.auctioneer.repository.CaptainRepository;
import com.auctioneer.repository.FieldingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PlayerStatsService {

    private final ObjectMapper objectMapper;
    private final BattingRepository battingRepository;
    private final BowlingRepository bowlingRepository;
    private final FieldingRepository fieldingRepository;
    private final CaptainRepository captainRepository;

    public PlayerStatsService(ObjectMapper objectMapper,
                              BattingRepository battingRepository,
                              BowlingRepository bowlingRepository,
                              FieldingRepository fieldingRepository,
                              CaptainRepository captainRepository) {
        this.objectMapper = objectMapper;
        this.battingRepository = battingRepository;
        this.bowlingRepository = bowlingRepository;
        this.fieldingRepository = fieldingRepository;
        this.captainRepository = captainRepository;
    }

    public void savePlayerStats(String json, PlayerEntity player) {
        try {
            PlayerStats playerStats = objectMapper.readValue(json, PlayerStats.class);

            saveStatistics(playerStats.getData().getStatistics().getBatting(), player, battingRepository, this::mapToBattingEntity);
            saveStatistics(playerStats.getData().getStatistics().getBowling(), player, bowlingRepository, this::mapToBowlingEntity);
            saveStatistics(playerStats.getData().getStatistics().getFielding(), player, fieldingRepository, this::mapToFieldingEntity);
            saveStatistics(playerStats.getData().getStatistics().getCaptain(), player, captainRepository, this::mapToCaptainEntity);

        } catch (Exception e) {
            log.error("Error saving player stats", e);
        }
    }

    private <T> void saveStatistics(List<PlayerStats.StatisticsEntry> statisticsEntries, PlayerEntity player,
                                    JpaRepository<T, Long> repository, BiFunction<PlayerStats.StatisticsEntry, PlayerEntity, T> mapper) {
        List<T> entities = statisticsEntries.stream()
                .map(entry -> mapper.apply(entry, player))
                .collect(Collectors.toList());
        repository.saveAll(entities);
    }

    private BattingStatisticsEntity mapToBattingEntity(PlayerStats.StatisticsEntry entry, PlayerEntity player) {
        BattingStatisticsEntity entity = new BattingStatisticsEntity();
        entity.setTitle(entry.getTitle());
        entity.setValue(entry.getValue());
        entity.setIsUserProperty(entry.getIsUserProperty());
        entity.setPlayer(player);
        return entity;
    }

    private BowlingStatisticsEntity mapToBowlingEntity(PlayerStats.StatisticsEntry entry, PlayerEntity player) {
        BowlingStatisticsEntity entity = new BowlingStatisticsEntity();
        entity.setTitle(entry.getTitle());
        entity.setValue(entry.getValue());
        entity.setIsUserProperty(entry.getIsUserProperty());
        entity.setPlayer(player);
        return entity;
    }

    private FieldingStatisticsEntity mapToFieldingEntity(PlayerStats.StatisticsEntry entry, PlayerEntity player) {
        FieldingStatisticsEntity entity = new FieldingStatisticsEntity();
        entity.setTitle(entry.getTitle());
        entity.setValue(entry.getValue());
        entity.setIsUserProperty(entry.getIsUserProperty());
        entity.setPlayer(player);
        return entity;
    }

    private CaptainStatisticsEntity mapToCaptainEntity(PlayerStats.StatisticsEntry entry, PlayerEntity player) {
        CaptainStatisticsEntity entity = new CaptainStatisticsEntity();
        entity.setTitle(entry.getTitle());
        entity.setValue(entry.getValue());
        entity.setIsUserProperty(entry.getIsUserProperty());
        entity.setPlayer(player);
        return entity;
    }

}
