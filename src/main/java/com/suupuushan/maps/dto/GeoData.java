package com.suupuushan.maps.dto;

import java.util.List;

public class GeoData {
  private String type;
  private List<Feature> features;

  public GeoData(List<Feature> features) {
    this.type = "FeatureCollection";
    this.features = features;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Feature> getFeatures() {
    return features;
  }

  public void setFeatures(List<Feature> features) {
    this.features = features;
  }

}
