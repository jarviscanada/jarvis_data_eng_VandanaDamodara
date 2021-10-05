package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.Model.Coordinates;
import ca.jrvs.apps.twitter.Model.Tweet;

public class TweetBuilder {

  public static Tweet buildTweet(String tweeterMessage, Double latitude, Double longitude) {
    Tweet tempTweet = new Tweet();
    Double[] coordinates = {latitude, longitude};
    Coordinates location = new Coordinates();
    String type = "Degrees.Minutes";
    location.setcoordinates(coordinates);
    location.setType(type);
    tempTweet.setText(tweeterMessage);
    tempTweet.setCoordinates(location);
    return tempTweet;

  }
}