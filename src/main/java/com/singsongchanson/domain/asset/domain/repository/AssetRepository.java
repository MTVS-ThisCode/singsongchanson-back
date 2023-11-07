package com.singsongchanson.domain.asset.domain.repository;

import com.singsongchanson.domain.asset.domain.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
