package com.suupuushan.maps.dto;

public class Geometry {
  private String type;
  private Object coordinates;

  public Geometry(String type, Object coordinates) {
    this.type = type;
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Object coordinates) {
    this.coordinates = coordinates;
  }
  
}
