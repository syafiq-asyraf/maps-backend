package com.suupuushan.maps.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity<?> getAllMarkers() {
    List<MarkerModel> markers = this.markerRepository.findAll();
    return new ResponseEntity<>(markers, HttpStatus.OK);
  }

  @PostMapping("/addMarker")
  public ResponseEntity<?> addMarker(@RequestBody MarkerModel markerModel){
    this.markerRepository.save(markerModel);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
