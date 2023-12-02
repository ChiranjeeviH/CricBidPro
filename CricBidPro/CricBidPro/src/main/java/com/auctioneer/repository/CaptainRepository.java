package com.auctioneer.repository;

import com.auctioneer.entity.CaptainStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptainRepository extends JpaRepository<CaptainStatisticsEntity,Long> {
}
