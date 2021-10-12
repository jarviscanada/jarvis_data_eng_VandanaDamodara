package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})

public class Entities {

  @JsonProperty("hashtags")
  private Hashtag[] hashTags = {};
  @JsonProperty("user_mentions")
  private UserMention[] userMentions = {};

  public Hashtag[] getHashTags() {
    return hashTags;
  }

  public void setHashTags(Hashtag[] hashTags) {
    this.hashTags = hashTags;
  }

  public UserMention[] getUserMentions() {
    return userMentions;
  }

  public void setUserMentions(UserMention[] userMentions) {
    this.userMentions = userMentions;
  }
}
