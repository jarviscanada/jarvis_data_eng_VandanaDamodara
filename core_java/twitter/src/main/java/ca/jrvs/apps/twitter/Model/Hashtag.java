package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Hashtag {
  @JsonProperty("indices")
  private List<Integer> indices;
  @JsonProperty("text")
  private String text;

  public List<Integer> getIndices() {
    return indices;
  }

  public void setIndices(List<Integer> indices) {
    this.indices = indices;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
