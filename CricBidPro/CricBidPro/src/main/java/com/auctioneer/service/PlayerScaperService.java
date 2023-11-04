package com.auctioneer.service;

import com.auctioneer.entity.Player;
import com.auctioneer.json.PlayerInfo;
import com.auctioneer.mapper.PlayerMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Log4j2
public class PlayerScaperService {

    @Autowired
    private final CricHeroSearchScraperService cricHeroSearchScraperService;
    @Autowired
    private final PlayerService playerService;

    private final PlayerMapper playerMapper = PlayerMapper.PLAYER_INSTANCE;

    public PlayerScaperService(CricHeroSearchScraperService cricHeroSearchScraperService, PlayerService playerService) {
        this.cricHeroSearchScraperService = cricHeroSearchScraperService;
        this.playerService = playerService;
    }

    public Player getCricHeroInfo(String cricHeroName) {
        log.warn("Getting CricHero Basic details");
        String response = cricHeroSearchScraperService.getUserInfo(cricHeroName);
        log.info("Response from Web Scrapper " + response);
        Player currentPlayer = null;
        if (Objects.nonNull(response)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                PlayerInfo playerInfo = objectMapper.readValue(response, PlayerInfo.class);
                if (playerInfo != null && playerInfo.getData() != null && playerInfo.getData().getPlayers() != null) {
                    for (PlayerInfo.Player player : playerInfo.getData().getPlayers()) {
                        Player playerEntity = playerMapper.responseToPlayerEntity(player);
                        log.info("Checking if User already exist in the table ");
                        currentPlayer = playerService.getPlayer(playerEntity.getUserId());
                        if (Objects.isNull(currentPlayer)) {
                            currentPlayer = playerService.savePlayer(playerEntity);
                            log.info("Successfully saved the player info in the database");
                        }
                    }
                }
            } catch (JsonProcessingException e) {
                log.error("Unable to parse scrapper response to PlayerInfo json class");
                throw new RuntimeException(e);
            }

        }

        return currentPlayer;

    }
}
