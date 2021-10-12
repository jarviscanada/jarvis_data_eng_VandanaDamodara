package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
public class TwitterServiceIntTest {
  TwitterService twitterService;
  @Before
  public void setUp() throws Exception{
    final String CONSUMER_KEY = System.getenv("consumerKey");
    final String CONSUMER_SECRET = System.getenv("consumerSecret");
    final String ACCESS_TOKEN = System.getenv("accessToken");
    final String TOKEN_SECRET = System.getenv("tokenSecret");

    TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
        ACCESS_TOKEN, TOKEN_SECRET);
    TwitterDao twitterDao = new TwitterDao(twitterHttpHelper);
    twitterService = new TwitterService(twitterDao);
    System.out.println(twitterDao);
    System.out.println(twitterHttpHelper);

  }

 @Test
  public void  deleteTweets() throws Exception{
  //id :1445947429514358793
//id : 1445945792137728000
String[] ids = {"1445945792137728000","1445947429514358793"};
    List<Tweet> returnedTweets = twitterService.deleteTweets(ids);
    System.out.println(JsonParser.toJson(returnedTweets, true, true));
    //System.out.println(returnedTweets.forEach(JsonParser::toJason(this, true, true));

  }




  @Test
  public void postTweet() throws Exception {
    String Tweeetmessage  = "Its a lovely day 4";
    double lat = 43.595310;
    double lon = -79.640579;
    Tweet newTweet = TweetBuilder.buildTweet(Tweeetmessage, lat, lon);
    System.out.println("out " +newTweet);

    Tweet returnedtweet = twitterService.postTweet(newTweet);
    System.out.println(JsonParser.toJson(returnedtweet, true, true));
    Assert.assertEquals("Its a lovely day 4",returnedtweet.getTweeterMessage());
  }

  @Test
  public void showTweet() throws Exception{
    String id = "1445768587671597062";
    Tweet returnedTweet = twitterService.showTweet(id,null);
    System.out.println(JsonParser.toJson(returnedTweet, true, true));
    assertEquals(id,returnedTweet.getIdString());
  }
}
**/