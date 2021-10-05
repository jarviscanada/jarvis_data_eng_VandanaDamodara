package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserMention {
  @JsonProperty("name")
  private String name;

  @JsonProperty("indices")
  private List<Integer> indices;

  @JsonProperty("screen_name")
  private String screen_name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Integer> getIndices() {
    return indices;
  }

  public void setIndices(List<Integer> indices) {
    this.indices = indices;
  }

  public String getScreen_name() {
    return screen_name;
  }

  public void setScreen_name(String screen_name) {
    this.screen_name = screen_name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getId_str() {
    return id_str;
  }

  public void setId_str(String id_str) {
    this.id_str = id_str;
  }

  @JsonProperty("id")
  private int id;

  @JsonProperty("id_str")
  private String id_str;


}
