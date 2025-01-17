package com.suupuushan.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suupuushan.maps.model.MarkerModel;

public interface MarkerRepository extends JpaRepository<MarkerModel, Long>{
  
}
