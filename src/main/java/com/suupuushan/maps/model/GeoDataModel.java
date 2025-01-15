package com.suupuushan.maps.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "geodata")
public class GeoDataModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "marker_count", nullable = true)
  private Integer markerCount;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "coordinates", nullable = true, columnDefinition = "json")
  private String coordinates;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMarkerCount() {
    return markerCount;
  }

  public void setMarkerCount(Integer markerCount) {
    this.markerCount = markerCount;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  // public String getCoordinates() {
  //   return coordinates;
  // }

  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  @Transient
  private List<List<Double>> flatCoordinates;

  @Transient
  private List<List<List<Double>>> nestedCoordinates;

  public GeoDataModel() {
  }

  public GeoDataModel(List<List<Double>> flatCoordinates) throws IOException {
      this.flatCoordinates = flatCoordinates;
      this.coordinatesJson = new ObjectMapper().writeValueAsString(flatCoordinates);
  }

  public CoordinateEntity(List<List<List<Double>>> nestedCoordinates) throws IOException {
      this.nestedCoordinates = nestedCoordinates;
      this.coordinatesJson = new ObjectMapper().writeValueAsString(nestedCoordinates);
  }

  public Object getCoordinates() throws IOException {
      if (coordinatesJson == null) {
          return null;
      }

      ObjectMapper mapper = new ObjectMapper();

      // Try deserializing as flat coordinates
      try {
          flatCoordinates = mapper.readValue(coordinatesJson, new TypeReference<List<List<Double>>>() {});
          return flatCoordinates;
      } catch (Exception e) {
          // If it fails, attempt deserializing as nested coordinates
      }

      nestedCoordinates = mapper.readValue(coordinatesJson, new TypeReference<List<List<List<Double>>>>() {});
      return nestedCoordinates;
  }

  public void setFlatCoordinates(List<List<Double>> flatCoordinates) throws IOException {
      this.flatCoordinates = flatCoordinates;
      this.coordinatesJson = new ObjectMapper().writeValueAsString(flatCoordinates);
  }

  public void setNestedCoordinates(List<List<List<Double>>> nestedCoordinates) throws IOException {
      this.nestedCoordinates = nestedCoordinates;
      this.coordinatesJson = new ObjectMapper().writeValueAsString(nestedCoordinates);
  }

  // public Long getId() {
  //     return id;
  // }

  // public void setId(Long id) {
  //     this.id = id;
  // }

  public String getCoordinatesJson() {
      return coordinatesJson;
  }

  public void setCoordinatesJson(String coordinatesJson) {
      this.coordinatesJson = coordinatesJson;
  }

}
