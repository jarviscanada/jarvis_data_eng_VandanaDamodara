package ca.jrvs.apps.twitter.dao;


import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.example.*;

import oauth.signpost.exception.*;
import java.io.*;
import java.net.*;
import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoTest {
  private TwitterDao twitterDao;
  @Before
  public void setUp() throws Exception {
    final String CONSUMER_KEY = System.getenv("consumerKey");
    final String CONSUMER_SECRET = System.getenv("consumerSecret");
    final String ACCESS_TOKEN = System.getenv("accessToken");
    final String TOKEN_SECRET = System.getenv("tokenSecret");
    TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
        ACCESS_TOKEN, TOKEN_SECRET);
    TwitterDao twitterDao = new TwitterDao(twitterHttpHelper);
    System.out.println("first "+ twitterDao);

  }

  @Test
  public void create()  throws Exception {
    String hashTag = "#Test_Case: ";
    String text = "It's a marvelous day";
    Double lat = 43.595310;;
    Double lon = -79.640579;
    Tweet postTweet =TweetBuilder.buildTweet(text, lon, lat);
    System.out.println("tweet"+ postTweet);
    Tweet tweet = twitterDao.create(postTweet);
    System.out.println(tweet);
    System.out.println(JsonParser.toJson(tweet, true, true));
  }

  @Test
  public void findById() {
  }

  @Test
  public void deleteById() {
  }
}