package com.suupuushan.maps.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
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

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "coordinates", nullable = true, columnDefinition = "text")
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
  private List<List<List<Double>>> flatCoordinates;

  @Transient
  private List<List<List<List<Double>>>> nestedCoordinates;

  public GeoDataModel() {
  }

  private GeoDataModel(String coordinates) {
    this.coordinates = coordinates;
  }

  public static GeoDataModel fromFlatCoordinates(List<List<List<Double>>> flatCoordinates) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      GeoDataModel entity = new GeoDataModel(mapper.writeValueAsString(flatCoordinates));
      entity.flatCoordinates = flatCoordinates;
      return entity;
  }

    public static GeoDataModel fromNestedCoordinates(List<List<List<List<Double>>>> nestedCoordinates) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      GeoDataModel entity = new GeoDataModel(mapper.writeValueAsString(nestedCoordinates));
      entity.nestedCoordinates = nestedCoordinates;
      return entity;
  }

  public Object getCoordinates() throws IOException {
      if (coordinates == null) {
          return null;
      }

      ObjectMapper mapper = new ObjectMapper();

      // Try deserializing as flat coordinates
      try {
          flatCoordinates = mapper.readValue(coordinates, new TypeReference<List<List<List<Double>>>>() {});
          return flatCoordinates;
      } catch (Exception e) {
          // If it fails, attempt deserializing as nested coordinates
      }

      nestedCoordinates = mapper.readValue(coordinates, new TypeReference<List<List<List<List<Double>>>>>() {});
      return nestedCoordinates;
  }

  public void setFlatCoordinates(List<List<List<Double>>> flatCoordinates) throws IOException {
      this.flatCoordinates = flatCoordinates;
      this.coordinates = new ObjectMapper().writeValueAsString(flatCoordinates);
  }

  public void setNestedCoordinates(List<List<List<List<Double>>>> nestedCoordinates) throws IOException {
      this.nestedCoordinates = nestedCoordinates;
      this.coordinates = new ObjectMapper().writeValueAsString(nestedCoordinates);
  }

  // public Long getId() {
  //     return id;
  // }

  // public void setId(Long id) {
  //     this.id = id;
  // }

  // public String getCoordinates() {
  //     return coordinates;
  // }

  // public void setCoordinates(String coordinates) {
  //     this.coordinates = coordinates;
  // }

}
