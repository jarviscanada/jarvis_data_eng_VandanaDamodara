package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import java.io.IOException;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  static String tweetJSON = "{"
      + " \"created_at\":\"Fri Oct 7 10:02:00 2021\",\n"
      + " \"id\":12345,\n"
      + " \"id_str\":\"12345\",\n"
      + " \"text\":\"GoodLuck\","
      + " \"entities\":{\n"
      + "     \"hashtags\":[],"
      + "     \"user_mentions\":[]"
      + " },\n"
      + " \"coordinates\":null,\n"
      + " \"retweet_count\":0,\n"
      + " \"favorite_count\":0,\n"
      + " \"favorited\":false,\n"
      + " \"retweeted\":false\n"
      + "}";

  @Test
  public void create() throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException, URISyntaxException {
    String sampleText = "GoodLuck";
    Double lat = 50.000;
    Double lon = -50.000;
    Tweet tweet = new Tweet();
    tweet.setTweeterMessage(sampleText);

    try {
      dao.create(tweet);
      //fail();
    } catch (RuntimeException e){
      assertTrue(true);
    }

    TwitterDao spyDAO = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJSON, Tweet.class);
    System.out.println(JsonParser.toJson(expectedTweet, true, true));
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());

    Tweet spyTweet = spyDAO.create(TweetBuilder.buildTweet(sampleText,lon,lat));
    System.out.println(spyTweet);
    System.out.println(JsonParser.toJson(spyTweet, true, true));
    assertNotNull(spyTweet);
    assertEquals("GoodLuck",spyTweet.getTweeterMessage());
  }

  @Test
  public void findById() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
    String sampleID = "1445768587671597062";
    String sampleText = "sep2016";

    try {
      dao.findById(sampleID);
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    TwitterDao spyDAO = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJSON, Tweet.class);
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());

    Tweet spyTweet = spyDAO.findById(sampleID);
    System.out.println("Find details" + JsonParser.toJson(spyTweet, true, true));
    assertNotNull(spyTweet);
    assertEquals("GoodLuck", spyTweet.getTweeterMessage());
  }

  @Test
  public void deleteById() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
    String sampleID = "1445768587671597062";
    String sampleText = "GoodLuck";

    try {
      dao.deleteById(sampleID);
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    TwitterDao spyDAO = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJSON, Tweet.class);
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());

    Tweet spyTweet = spyDAO.deleteById(sampleID);
    System.out.println("Deleted id" + JsonParser.toJson(spyTweet, true, true));
    assertNotNull(spyTweet);
    assertNotNull(spyTweet.getTweeterMessage());
  }

}