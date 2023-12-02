package com.auctioneer.service;

import com.auctioneer.entity.PlayerEntity;
import com.auctioneer.json.PlayerInfo;
import com.auctioneer.mapper.PlayerMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Log4j2
public class PlayerScaperService {

    private final CricHeroSearchScraperService cricHeroSearchScraperService;
    private final PlayerService playerService;
    private final PlayerStatsService playerStatsService;
    private final PlayerMapper playerMapper = PlayerMapper.PLAYER_INSTANCE;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PlayerScaperService(CricHeroSearchScraperService cricHeroSearchScraperService,
                               PlayerService playerService,
                               PlayerStatsService playerStatsService) {
        this.cricHeroSearchScraperService = cricHeroSearchScraperService;
        this.playerService = playerService;
        this.playerStatsService = playerStatsService;
    }

    public PlayerEntity getPlayerInfo(String playerName)  {
        return fetchOrCreatePlayer(playerName);
    }

    public String getPlayerFullProfile(String playerName) {
        PlayerEntity playerEntity = fetchOrCreatePlayer(playerName);
        String response = cricHeroSearchScraperService.getUserStats(String.valueOf(playerEntity.getPlayerId()));
        playerStatsService.savePlayerStats(response,playerEntity);
        return response;
    }

    private PlayerEntity fetchOrCreatePlayer(String cricHeroName) {
        PlayerEntity currentPlayer = playerService.getPlayerByExactName(cricHeroName);

        if (currentPlayer == null) {
            log.info("User doesn't exist in the database, scraping CricHero's website");
            String response = cricHeroSearchScraperService.getUserInfo(cricHeroName);

            if (Objects.nonNull(response)) {
                currentPlayer = createAndSavePlayerFromResponse(response);
            }
        }

        return currentPlayer;
    }

    private PlayerEntity createAndSavePlayerFromResponse(String response) {
        try {
            PlayerInfo playerInfo = objectMapper.readValue(response, PlayerInfo.class);
            return savePlayerFromInfo(playerInfo);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse scraper response to PlayerInfo JSON class", e);
            throw new RuntimeException("Failed to process player information", e);
        }
    }

    private PlayerEntity savePlayerFromInfo(PlayerInfo playerInfo) {
        PlayerEntity currentPlayer = null;
        if (playerInfo != null && playerInfo.getData() != null && playerInfo.getData().getPlayers() != null) {
            for (PlayerInfo.Player player : playerInfo.getData().getPlayers()) {
                PlayerEntity playerEntity = playerMapper.responseToPlayerEntity(player);
                currentPlayer = playerService.getPlayer(playerEntity.getPlayerId());

                if (Objects.isNull(currentPlayer)) {
                    currentPlayer = playerService.savePlayer(playerEntity);
                    log.info("Successfully saved the player info in the database");
                }
            }
        }
        return currentPlayer;
    }
}
