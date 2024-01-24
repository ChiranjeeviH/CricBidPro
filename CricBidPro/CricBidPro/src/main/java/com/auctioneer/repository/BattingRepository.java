package com.auctioneer.repository;

import com.auctioneer.entity.BattingStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattingRepository extends BaseStatisticsRepository {
}
