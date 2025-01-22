package com.suupuushan.maps.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suupuushan.maps.model.MarkerModel;
import com.suupuushan.maps.repository.MarkerRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/marker")
public class MarkerApi {

  @Autowired
  private MarkerRepository markerRepository;
  
  @GetMapping("")
  public ResponseEntity<?> getAllMarkers(@RequestParam Optional<Long> parentId) {
    List<MarkerModel> markers = new ArrayList<>();
    if (parentId.isPresent()) {
      markers = this.markerRepository.findByParentId(parentId);
    } else {
      markers = this.markerRepository.findAllByOrderByIdAsc();
    }
    return new ResponseEntity<>(markers, HttpStatus.OK);
  }

  @Transactional
  @PostMapping("/addMarker")
  public ResponseEntity<?> addMarker(@RequestBody MarkerModel markerModel){
    // this.markerRepository.save(markerModel);
    this.markerRepository.addMarker(markerModel.getLat(), markerModel.getLng(), markerModel.getParentId());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Transactional
  @PutMapping("/editMarker/{id}")
  public ResponseEntity<?> editMarker(@PathVariable Long id, @RequestBody MarkerModel markerModel){
    // MarkerModel marker = this.markerRepository.findById(id).orElse(null);
    // marker.setLat(markerModel.getLat());
    // marker.setLng(markerModel.getLng());
    // this.markerRepository.save(marker);
    this.markerRepository.updateMarker(id, markerModel.getLat(), markerModel.getLng());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Transactional
  @DeleteMapping("/deleteMarker/{id}")
  public ResponseEntity<?> deleteMarker(@PathVariable Long id){
    // this.markerRepository.deleteById(id);
    this.markerRepository.deleteMarker(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
