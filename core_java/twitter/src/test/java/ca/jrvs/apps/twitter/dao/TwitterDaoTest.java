package ca.jrvs.apps.twitter.dao;


import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.example.*;

import oauth.signpost.exception.*;
import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.http.util.EntityUtils;
import org.junit.*;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.Model.Tweet;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoTest {

  private TwitterDao twitterDao;

  @Before
  public void setUp() {
    final String CONSUMER_KEY = System.getenv("consumerKey");
    final String CONSUMER_SECRET = System.getenv("consumerSecret");
    final String ACCESS_TOKEN = System.getenv("accessToken");
    final String TOKEN_SECRET = System.getenv("tokenSecret");

    TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
        ACCESS_TOKEN, TOKEN_SECRET);
    twitterDao = new TwitterDao(twitterHttpHelper);
    System.out.println(twitterDao);
    System.out.println(twitterHttpHelper);

  }

  @Test
  public void create() throws Exception {
    String tweeterMessage = "It's a working day";
    double lat = 43.595310;
    double lon = -79.640579;
    Tweet newTweet = TweetBuilder.buildTweet(tweeterMessage, lat, lon);

    /*TODO: How to add tweeter mentions and hashtags from the message
    String hashTagText = "hashtag01";
    int[] hashTagIndex = {1,2};
    HashTag hashTag = new HashTag();
    hashTag.setHashTagText(hashTagText);
    hashTag.setIndexArray(hashTagIndex);
    HashTag[] hashTags = {hashTag};
    Entities entity = EntityBuilder.buildEntity(hashTags, null);
    newTweet.setEntity(entity);
    */



    //test if the object is properly created
    System.out.println(JsonParser.toJson(newTweet, true, true));
    assertEquals(tweeterMessage, newTweet.getTweeterMessage());
    assertNotNull(newTweet.getLocation());
    assertEquals(lat, newTweet.getLocation().getLatitude(), 0);
    assertEquals(lon, newTweet.getLocation().getLongitude(), 0);

    Tweet returnedTweet = twitterDao.create(newTweet);
    System.out.println(JsonParser.toJson(returnedTweet, true, true));
  }



  @Test
  public void findById() throws Exception {

    // ACTUAL TWEET:  This is a sample tweet Its a beautiful day!
    String id = "1445768587671597062";
    Tweet returnedTweet = twitterDao.findById(id);
    System.out.println(JsonParser.toJson(returnedTweet, true, true));

    assertEquals(id, returnedTweet.getIdString());
    //System.out.println(returnedTweet.getEntity().getUserMentions()[0].getScreenName());
    assertEquals("It's a Beautiful day", returnedTweet.getTweeterMessage());

  }
}

  //Delete the tweet with id 1445774792141852681 message "Its a Working day"
  @Test
  public void deleteById() {
    Tweet returnedTweet = twitterDao.deleteById("1445774792141852681");
    //System.out.println(JsonParser.toJson(returnedTweet, true, true));

    assertEquals("1445774792141852681", returnedTweet.getIdString());
  }



}