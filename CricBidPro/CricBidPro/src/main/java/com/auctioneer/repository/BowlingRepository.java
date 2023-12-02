package com.auctioneer.repository;

import com.auctioneer.entity.BowlingStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BowlingRepository extends JpaRepository<BowlingStatisticsEntity,Long> {
}
