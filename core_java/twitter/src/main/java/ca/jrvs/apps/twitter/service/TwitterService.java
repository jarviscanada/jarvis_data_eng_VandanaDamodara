package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.CrdDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service{
  private CrdDao<Tweet,String> twitterDao;
@Autowired
  public TwitterService(CrdDao<Tweet,String> twitterDao)
  {
    this.twitterDao =twitterDao;
  }
  public void validateTweet(Tweet tweet) {
    final int TWITTER_LENGTH = 200;
    final Double LATITUDE_MAX = 90.0000;

    final Double LONGITUDE_MAX = 180.0000;
    final Double[] NORTH_POLE = {90.0000, 135.0000};
    String tweeterMessage = tweet.getTweeterMessage();
    Double Latitude = tweet.getLocation().getLatitude();
    Double Longitude = tweet.getLocation().getLongitude();
    if (tweeterMessage.length() > TWITTER_LENGTH) {
      String tempMessage = tweeterMessage.substring(0, TWITTER_LENGTH - 1);
      tweet.setTweeterMessage(tempMessage);

    }
    if (tweet.getLocation() != null) {
      if (Longitude < -180.0 || Longitude > 180.0) {
        throw new IllegalArgumentException("Longitude range out of range");

      }
      if (Latitude < -90 || Latitude > 90) {
        throw new IllegalArgumentException("Latitude out of range");
      }
    }
  }
  private boolean isValidID(String id){
    if (!id.matches("[0-9]+")) {
      throw new IllegalArgumentException("ID:" + id + "must only contain numbers");
    }
    return true;
  }
  public Tweet postTweet(Tweet tweet) {
    validateTweet(tweet);
    return twitterDao.create(tweet);

  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    if(isValidID(id))
    {
      return twitterDao.findById(id);
    }
  else
    {
      throw new IllegalArgumentException("Nota valid Id");
    }
  }



  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> deletedTweets = new ArrayList<>();
    for (String id :ids){
      if (isValidID(id))
      {
        deletedTweets.add(twitterDao.deleteById(id));
      }
      else
      {
        throw new IllegalArgumentException("Not a valid ID for delete");
      }
    }
    return deletedTweets;
  }
}
