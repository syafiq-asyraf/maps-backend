package com.suupuushan.maps.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suupuushan.maps.dto.Feature;
import com.suupuushan.maps.dto.GeoData;
import com.suupuushan.maps.dto.Geometry;
import com.suupuushan.maps.dto.Properties;
import com.suupuushan.maps.model.GeoDataModel;
import com.suupuushan.maps.repository.GeoDataRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/geodata")
public class GeoDataApi {

  @Autowired
  private GeoDataRepository geoDataRepository;

  @GetMapping("")
  public ResponseEntity<?> getAllGeoData() throws IOException {
    List<GeoDataModel> data = this.geoDataRepository.findAll();

    List<Feature> features = new ArrayList<>();

    for (GeoDataModel geoData : data) {
      Properties props = new Properties(geoData.getName(), geoData.getMarkerCount());
      Geometry geo = new Geometry(geoData.getType(), geoData.getCoordinates());
      Feature feature = new Feature(geoData.getId(), props, geo);
      features.add(feature);
    }

    GeoData geoData = new GeoData(features);
    
    return new ResponseEntity<>(geoData, HttpStatus.OK);
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> updateMarkerCount(@PathVariable Long id){
    GeoDataModel data = this.geoDataRepository.findById(id).orElse(null);
    data.setMarkerCount(30);
    this.geoDataRepository.save(data);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
}