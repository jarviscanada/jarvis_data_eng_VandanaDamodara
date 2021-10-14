package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {
  TwitterController twitterController;
  @Before
  public void setUp() throws Exception{
    final String CONSUMER_KEY = System.getenv("consumerKey");
    final String CONSUMER_SECRET = System.getenv("consumerSecret");
    final String ACCESS_TOKEN = System.getenv("accessToken");
    final String TOKEN_SECRET = System.getenv("tokenSecret");

    TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
        ACCESS_TOKEN, TOKEN_SECRET);
    TwitterDao twitterDao = new TwitterDao(twitterHttpHelper);
    TwitterService twitterService = new TwitterService(twitterDao);
    twitterController= new TwitterController(twitterService);
    System.out.println(twitterDao);
    System.out.println(twitterHttpHelper);
  }

@Test
  public void postTweet() throws Exception{
    String tweetermessage = "Hope for the best 2";
    //String tweetermes = "best of luck #abc";
    String[] args={"post",tweetermessage,"43.56789:-79.56432"};
    //String[] arg={"post",tweetermes,"43.56789:-79.56432"};
    Tweet returnedTweet = twitterController.postTweet(args);
    //Tweet returned = twitterController.postTweet(arg);
    System.out.println(JsonParser.toJson(returnedTweet, true, true));
    //System.out.println(JsonParser.toJson(returned, true, true));
    assertEquals("Hope for the best 2",returnedTweet.getTweeterMessage());
    //assertEquals("abc",returned.getEntity().getHashTags()[0].getText());
  }

  @Test
  public void showTweet() throws Exception {
    //ID: 1446120720119975945: best of luck #abc
    String id = "1446120720119975945";
    String[] args = {"show", id};
    Tweet returnedTweet = twitterController.showTweet(args);
    System.out.println(JsonParser.toJson(returnedTweet,true,true));
    assertEquals(id,returnedTweet.getIdString());

  }

  @Test
  public void deleteTweet() throws Exception {
    //id :1446122685931474950
    String[] args = {"delete","1446122685931474950"};
    List<Tweet> returnedTweets = twitterController.deleteTweet(args);
    System.out.println("delete"+JsonParser.toJson(returnedTweets,true,true));
    assertNotNull(returnedTweets);
  }
}