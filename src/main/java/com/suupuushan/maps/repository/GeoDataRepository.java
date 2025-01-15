package com.suupuushan.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suupuushan.maps.model.GeoDataModel;

public interface GeoDataRepository extends JpaRepository<GeoDataModel, Long> {
}
