package com.auctioneer.controller;

import com.auctioneer.entity.PlayerEntity;
import com.auctioneer.entity.PlayerInfoWithStatsResponse;
import com.auctioneer.service.PlayerScaperService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
@Log4j2
public class PlayerSearchController {
    private final PlayerScaperService playerScaperService;

    public PlayerSearchController(PlayerScaperService playerScaperService) {
        this.playerScaperService = playerScaperService;
    }

    @GetMapping("/basicInfo/{playerName}")
    public PlayerEntity fetchBasicUserInfo(@PathVariable String playerName) {
        return playerScaperService.getPlayerInfo(playerName);
    }

    @GetMapping("/fullProfile/{playerName}")
    public PlayerInfoWithStatsResponse fetchPlayerFullProfile(@PathVariable String playerName) {
        return playerScaperService.getPlayerFullProfile(playerName);
    }

}
