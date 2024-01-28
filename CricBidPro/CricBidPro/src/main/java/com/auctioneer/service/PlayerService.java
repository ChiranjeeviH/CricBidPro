package com.auctioneer.service;

import com.auctioneer.entity.PlayerEntity;
import com.auctioneer.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerEntity savePlayer(PlayerEntity player) {
        return playerRepository.save(player);
    }

    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.findAll();
    }

    public PlayerEntity getPlayer(Long userId){
        return playerRepository.findByPlayerId(userId);
    }

    public PlayerEntity getPlayerByExactName(String name){
        return playerRepository.findByName(name);
    }

    public List<String> getAllPlayerNames(String profile) {

        return profile.isBlank() ? playerRepository.findAll().stream().map(PlayerEntity::getName).collect(Collectors.toList())
                : playerRepository.findAll().stream().filter(emp -> emp.getPlayerSkill().equals(profile)).map(PlayerEntity::getName).collect(Collectors.toList());
    }

}
