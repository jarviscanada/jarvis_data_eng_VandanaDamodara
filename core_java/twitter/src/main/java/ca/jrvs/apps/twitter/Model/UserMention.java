package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "indices",
    "screen_name",
    "id",
    "id_str"
})

public class UserMention {

  @JsonProperty("name")
  private String name;
  @JsonProperty("indices")
  private int[] indexArray;
  @JsonProperty("screen_name")
  private String screenName;
  @JsonProperty("id")
  private long id;
  @JsonProperty("id_str")
  private String idString;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int[] getIndexArray() {
    return indexArray;
  }

  public void setIndexArray(int[] indexArray) {
    this.indexArray = indexArray;
  }

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getIdString() {
    return idString;
  }

  public void setIdString(String idString) {
    this.idString = idString;
  }

}
/**

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
**/