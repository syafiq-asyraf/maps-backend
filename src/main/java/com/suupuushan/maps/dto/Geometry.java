package com.suupuushan.maps.dto;

import java.util.List;

public class Geometry {
  private String type;
  private List<String> coordinates;

  public Geometry(String type, List<String> coordinates) {
    this.type = type;
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<String> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<String> coordinates) {
    this.coordinates = coordinates;
  }

  
}
