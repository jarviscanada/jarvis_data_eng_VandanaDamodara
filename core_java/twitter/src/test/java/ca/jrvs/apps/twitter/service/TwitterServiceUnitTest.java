package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @Mock
  TwitterDao twitterDao;
@InjectMocks
TwitterService twitterService;


  @Test
  public void postTweet() throws Exception {

    when(twitterDao.create(any()))
        .thenReturn(TweetBuilder.buildTweet("Good Evening", 43.595310, -79.640579));

    try {
      twitterService.postTweet(TweetBuilder.buildTweet("Bad Coordinates", 1000.0, 1000.0));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    Tweet returnedTweet = twitterService
        .postTweet(TweetBuilder.buildTweet("Good Morning Agent 099", 43.595310, -79.640579));
    assertNotNull(returnedTweet);
    assertEquals("Good Evening",returnedTweet.getTweeterMessage());
    assertNotNull(returnedTweet.getTweeterMessage());
  }


  @Test
  public void showTweet() {
    String tweeterMessage = "Good Evening Friends";
    Double lat = 43.595310;
    Double lon = -79.640579;
    when(twitterDao.findById(any()))
        .thenReturn(TweetBuilder.buildTweet(tweeterMessage, lat, lon));
    try {
      twitterService.showTweet("14457BABY", null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    Tweet returnedTweet = twitterService.showTweet("1445768587671597062",null);
    assertNotNull(returnedTweet);
    assertNotNull(returnedTweet.getTweeterMessage());
    assertEquals("Good Evening Friends",returnedTweet.getTweeterMessage());
  }

  @Test
  public void deleteTweets() {
    String tweeterMessage = "Good Morning All";
    Double lat = 43.595310;
    Double lon = -79.640579;
    when(twitterDao.deleteById(any())).thenReturn(TweetBuilder.buildTweet(tweeterMessage,lat,lon));
    String[] badIDs = {"137818594BABY524675", "13781440GOAT83722244"};
    try{
      twitterService.deleteTweets(badIDs);
      fail();
    }
    catch (IllegalArgumentException e)
    {
      assertTrue(true);
    }
    String[] goodIDs = {"1378185319594524675", "1378187944083722244"};
    List<Tweet> returnedTweets = twitterService.deleteTweets(goodIDs);
    assertNotNull(returnedTweets);
    //assertEquals("Good Morning All",returnedTweets.getTweeterMessage());
  }
}