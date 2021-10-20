package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.Model.Coordinates;
import ca.jrvs.apps.twitter.Model.Entities;
import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.w3c.dom.Entity;
import org.w3c.dom.stylesheets.LinkStyle;

@RunWith(MockitoJUnitRunner.class)



public class TwitterControllerUnitTest {
  @Mock
  TwitterService twitterService;

  @InjectMocks
  TwitterController twitterController;
  private static Tweet tweet = new Tweet();


@Test
  public void postTweet() throws Exception{
    when(twitterService.postTweet(notNull()))
        .thenReturn(TweetBuilder.buildTweet("Good Afternoon", 43.595310, -79.640579));

    try {
      twitterController.postTweet(new String[]{"post", "text only"});
      fail();
    } catch (IllegalArgumentException e) {

      assertTrue(true);
    }

    try {
      twitterController.postTweet(new String[]{"post", "test", "101"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      twitterController.postTweet(new String[]{"post", "test", "18:32:12"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      twitterController.postTweet(new String[]{"post", "test", ":32"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    String tweeterMessage = "It's a marvelous day #agent444";
    String[] args = {"post", tweeterMessage, "43.595310:-79.640579"};
    Tweet returnedTweet = twitterController.postTweet(args);
    assertNotNull(returnedTweet);
    assertEquals("Good Afternoon",returnedTweet.getTweeterMessage());
    assertNotNull(returnedTweet.getTweeterMessage());
  }

    /**when(twitterService.postTweet(any())).thenReturn(tweet);
    String tweetermessage = "test post";
    String[] args = {"post",tweetermessage,"1.0:12.00"};
    Tweet response= twitterController.postTweet(args);
    System.out.println(JsonParser.toJson(response, true, true));
    assertEquals("test post",response.getTweeterMessage());
  }**/

    @Test
    public void showTweet() {
      when(twitterService.showTweet(notNull(), any()))
          .thenReturn(TweetBuilder.buildTweet("Good evening", 43.595310, -79.640579));

      try {
        twitterController.showTweet(new String[]{"show", "text only", "etc", "etc"});
        fail();
      }
      catch (IllegalArgumentException e)
      {
        assertTrue(true);
      }
      try {
        twitterController.showTweet(new String[]{"show"});
            fail();
        }
      catch (IllegalArgumentException e)
      {
      assertTrue(true);
      }
      String[] args={"show","1379420134557687809"};
      Tweet returnedTweet = twitterController.showTweet(args);
      assertNotNull(returnedTweet);
      assertEquals("Good evening",returnedTweet.getTweeterMessage());
      assertNotNull(returnedTweet.getTweeterMessage());
    }
  @Test
  public void deleteTweet() throws Exception {
    when(twitterService.deleteTweets(notNull())).thenReturn(new ArrayList<Tweet>());

    try {
      twitterController.deleteTweet(new String[]{"delete", "1378185319594524675", "1378185319594524675"});
      //fail();
    }
    catch (IllegalArgumentException e)
    {
      assertTrue(true);
    }
    String[] args = {"delete", "13794337832046678" + "," + "137806156348412547"};
    List<Tweet> returnedTweets = twitterController.deleteTweet(args);
    assertNotNull(returnedTweets);
}
}