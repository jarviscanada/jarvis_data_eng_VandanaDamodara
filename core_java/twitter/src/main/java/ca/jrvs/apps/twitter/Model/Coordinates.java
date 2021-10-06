package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Coordinates {

  @JsonProperty("coordinates")
  private Double[] coordinates;
  @JsonProperty("type")
  private String type;

  public Double[] getcoordinates() {
    return coordinates;
  }

  public void setcoordinates(Double[] coordinates) {
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  public Double getLatitude() { return coordinates[0];}
  public Double getLongitude() { return coordinates[1];}

}
