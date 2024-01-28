package com.auctioneer.controller;

import com.auctioneer.entity.PlayerEntity;
import com.auctioneer.entity.PlayerInfoWithStatsResponse;
import com.auctioneer.service.PlayerScaperService;
import com.auctioneer.service.PlayerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
public class PlayerSearchController {
    private final PlayerScaperService playerScaperService;

    private final PlayerService playerService;

    public PlayerSearchController(PlayerScaperService playerScaperService, PlayerService playerService) {
        this.playerScaperService = playerScaperService;
        this.playerService = playerService;
    }

    @GetMapping("/basicInfo/{playerName}")
    public ResponseEntity<PlayerEntity> fetchBasicUserInfo(@PathVariable String playerName) {
        return Optional.ofNullable(playerScaperService.getPlayerInfo(playerName))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/fullProfile/{playerName}")
    public ResponseEntity<PlayerInfoWithStatsResponse> fetchPlayerFullProfile(@PathVariable String playerName) {
        return Optional.ofNullable(playerScaperService.getPlayerFullProfile(playerName))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/allPlayerNames/{profile}")
    public ResponseEntity<List<String>> getAllPlayersNames(@PathVariable(required = false) String profile) {

        return Optional.ofNullable(playerService.getAllPlayerNames(profile))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/allPlayerNames")
    public List<String> getAllPlayersNames() {

        return Arrays.asList("Chiranjeevi Haridasula", "Harish", "Sandhya");
    }

}
