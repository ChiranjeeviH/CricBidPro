package com.auctioneer.repository;

import com.auctioneer.entity.FieldingStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldingRepository extends BaseStatisticsRepository {
}
