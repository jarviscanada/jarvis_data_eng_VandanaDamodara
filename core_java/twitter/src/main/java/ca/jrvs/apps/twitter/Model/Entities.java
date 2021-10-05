package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Entities {
  @JsonProperty("hashtags")
  private List<Double> hashtags;
  @JsonProperty("user_mentions")
  private List<UserMention> userMentions;

  public List<Double> getHashtags() {
    return hashtags;
  }

  public void setHashtags(List<Double> hashtags) {
    this.hashtags = hashtags;
  }

  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  public void setUserMentions(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }
}
