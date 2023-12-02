package com.auctioneer.repository;

import com.auctioneer.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    // Define custom query methods if needed

    PlayerEntity findByPlayerId(long userid);

    PlayerEntity findByName(String name);
}
