package com.suupuushan.maps.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // List<List<Double>> flatCoordinates = Arrays.asList(
    //     Arrays.asList(-87.359296, 35.00118),
    //     Arrays.asList(-85.606675, 34.984749)
    // );

    // GeoDataModel entity = GeoDataModel.fromFlatCoordinates(flatCoordinates);
	// entity.setName("AAa");
	// entity.setType("Poly");

	// geoDataRepository.save(entity);
    
    // List<List<List<Double>>> nestedCoordinates = Arrays.asList(
    //     Arrays.asList(
    //         Arrays.asList(-87.359296, 35.00118),
    //         Arrays.asList(-85.606675, 34.984749)
    //     ),
    //     Arrays.asList(
    //         Arrays.asList(-85.431413, 34.124869)
    //     )
    // );

	// GeoDataModel entity = GeoDataModel.fromNestedCoordinates(nestedCoordinates);
    
    return new ResponseEntity<>(geoData, HttpStatus.OK);
  }

  // public void saveFlatCoordinates(List<List<Double>> flatCoordinates) throws IOException {
  //     GeoDataModel entity = new GeoDataModel(flatCoordinates);
      
  // }
  
}