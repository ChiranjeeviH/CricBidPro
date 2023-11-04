package com.auctioneer.repository;

import com.auctioneer.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Define custom query methods if needed

    Player findByUserId(long userid);
}
