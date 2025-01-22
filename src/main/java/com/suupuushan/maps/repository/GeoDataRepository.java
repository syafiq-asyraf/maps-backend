package com.suupuushan.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suupuushan.maps.model.GeoDataModel;

@Repository
public interface GeoDataRepository extends JpaRepository<GeoDataModel, Long> {
}
