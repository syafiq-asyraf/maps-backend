package com.suupuushan.maps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suupuushan.maps.model.MarkerModel;

@Repository
public interface MarkerRepository extends JpaRepository<MarkerModel, Long>{
  // List<MarkerModel> findAllByOrderByIdAsc();
  // List<MarkerModel> findByParentId(Optional<Long> parentId);

  // native query
  @Query(value = "SELECT * FROM markers m ORDER BY m.id ASC", nativeQuery = true)
  List<MarkerModel> findAllByOrderByIdAsc();

  @Query(value = "SELECT * FROM markers m WHERE m.parent_id = :parentId", nativeQuery = true)
  List<MarkerModel> findByParentId(Optional<Long> parentId);

  @Modifying
  @Query(value = "INSERT INTO markers (lat, lng, parent_id) VALUES (:lat, :lng, :parentId)", nativeQuery = true)
  void addMarker(Double lat, Double lng, Long parentId);

  @Modifying
  @Query(value = "UPDATE markers SET lat = :lat, lng = :lng WHERE id = :id", nativeQuery = true)
  void updateMarker(Long id, Double lat, Double lng);

  @Modifying
  @Query(value = "DELETE FROM markers WHERE id = :id", nativeQuery = true)
  void deleteMarker(Long id);
}
