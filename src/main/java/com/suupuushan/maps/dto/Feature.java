package com.suupuushan.maps.dto;

public class Feature {
  private String type;
  private Long id;
  private Properties properties;
  private Geometry geometry;

  public Feature(Long id, Properties properties, Geometry geometry){
    this.type = "Feature";
    this.id = id;
    this.properties = properties;
    this.geometry = geometry;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public Geometry getGeometry() {
    return geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  
}
