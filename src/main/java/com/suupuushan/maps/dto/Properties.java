package com.suupuushan.maps.dto;

public class Properties {
  private String name;
  private Integer markerCount;
  
  public Properties(String name, Integer markerCount){
    this.name = name;
    this.markerCount = markerCount;
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

  
}
