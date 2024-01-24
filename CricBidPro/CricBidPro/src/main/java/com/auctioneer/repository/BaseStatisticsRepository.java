package com.auctioneer.repository;

import com.auctioneer.entity.BaseStatisticsEntity;
import com.auctioneer.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseStatisticsRepository extends JpaRepository<BaseStatisticsEntity, Long> {

     List<BaseStatisticsEntity> findByPlayer(PlayerEntity playerEntity);
}
