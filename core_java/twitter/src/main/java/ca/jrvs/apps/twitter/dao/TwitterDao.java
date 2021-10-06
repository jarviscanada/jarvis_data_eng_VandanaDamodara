package ca.jrvs.apps.twitter.dao;


import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.Model.Coordinates;
import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.example.JsonParser;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDao implements CrdDao<Tweet, String> {

  // TWITTER resource urls
  private static final String TWITTER_BASE_URI = "https://api.twitter.com/1.1/";
  private static final String TWITTER_POST_URI = TWITTER_BASE_URI + "statuses/update.json";
  private static final String TWITTER_SHOW_URI = TWITTER_BASE_URI + "statuses/show.json";
  private static final String TWITTER_DELETE_URI = TWITTER_BASE_URI + "statuses/destroy/";

  // TWITTER resource symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  // Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;
  private Logger logger = LoggerFactory.getLogger(TwitterDao.class);

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    // checks response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        logger.error("Response has no entity", e);
      }
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }
    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    // converts response entity to string
    String responseString;
    try {
      responseString = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert JSON to string");
    }

    // deserializes the string format of the response to a Tweet object
    try {
      tweet = JsonParser.toObjectFromJson(responseString, Tweet.class);
    } catch (IOException e) {
     throw new RuntimeException("Unable to convert JSON string to a Tweet object");
    }
    return tweet;
  }

  private URI getTweetUri(Tweet tweet) throws URISyntaxException {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    String tempUriInput = "";
    String tweeterMessage = tweet.getTweeterMessage();
    String escapedTweeterMessage = percentEscaper.escape(tweeterMessage);
    Coordinates tweeterLocation = tweet.getLocation();

    /*
    TODO: How to add tweeter mentions and hashtags to the message
    UserMention[] tweeterMentions = tweet.getEntity().getUserMentions();
    HashTag[] tweeterHashtags = tweet.getEntity().getHashTags();
     */

    try {
      // POST https://api.twitter.com/1.1/statuses/update.json?status=MESSAGE
      // adds message
      tempUriInput = QUERY_SYM + "status" + EQUAL + escapedTweeterMessage;
      // adds tweet location
      if (tweeterLocation != null) {
        Double longitude = tweet.getLocation().getcoordinates()[0];
        Double latitude = tweet.getLocation().getcoordinates()[1];

        // URLEncoder class contains static methods for converting a String to the <CODE>application/x-www-form-urlencoded</CODE> MIME format.
        tempUriInput += AMPERSAND + "long" + EQUAL + URLEncoder
            .encode(Double.toString(longitude), StandardCharsets.UTF_8.name());
        tempUriInput += AMPERSAND + "lat" + EQUAL + URLEncoder
            .encode(Double.toString(latitude), StandardCharsets.UTF_8.name());

      }
    } catch (UnsupportedEncodingException e) {
      logger.error("Encoding not supported: " + StandardCharsets.UTF_8.name(), e);
      throw new RuntimeException("Encoding not supported ", e);
    }
    return new URI(TWITTER_POST_URI + tempUriInput);

  }

  @Override
  public Tweet create(Tweet tweet) {
    // POST https://api.twitter.com/1.1/statuses/update.json?status=MESSAGE
    URI tweeterUri;
    try {
      tweeterUri = getTweetUri(tweet);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    // executes the HTTP Post request via httpHelper

    HttpResponse response = httpHelper.httpPost(tweeterUri);
    System.out.println(response);

    // validates and deserialize response to a Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet findById(String id) {
    // GET https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672
    URI tweeterUri;
    String tempUriInput = QUERY_SYM + "id" + EQUAL + id;
    try {
      tweeterUri = new URI(TWITTER_SHOW_URI + tempUriInput);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("String could not be parsed as a URI", e);
    }

    // executes the HTTP Get request via httpHelper
    HttpResponse response = httpHelper.httpGet(tweeterUri);

    // validates and deserialize response to a Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String id) {
    //POST https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json
    URI tweeterUri;
    String tempUriInput = id + ".json";
    try {
      tweeterUri = new URI(TWITTER_DELETE_URI + tempUriInput);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("String could not be parsed as URI", e);
    }
    // executes the HTTP Post request via httpHelper
    HttpResponse response = httpHelper.httpPost(tweeterUri);

    // validates and deserialize response to a Tweet object
    return parseResponseBody(response, HTTP_OK);
  }
}
/**import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import jdk.internal.dynalink.beans.StaticClass;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet, String>
{
  public static final String API_BASE_URI = "https://api.twitter.com";
  public static final String POST_PATH = "1.1/statuses/update.json";
  public static final String SHOW_PATH = "1.1/statuses/show.json";
  public static final String DELETE_PATH = "1.1/statuses/destroy";

  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL  = "=";

  private static final int HTTP_OK   = 200;
  private HttpHelper httpHelper;
  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;

  }
  @Override
    public Tweet create (Tweet tweet){
    PercentEscaper percentEscaper = new PercentEscaper("", false);
      URI uri;
      try {
  uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL
  + percentEscaper.escape(tweet.getText()) + AMPERSAND + "long" + EQUAL
  + tweet.getCoordinates().getcoordinates()[0].toString()
  + AMPERSAND + "lat" + EQUAL +
  tweet.getCoordinates().getcoordinates()[1].toString());
  //uri = getPostUri(tweet);

      } catch (URISyntaxException  e) {
        throw new IllegalArgumentException("Invalid  tweet input", e);
      }
      HttpResponse response = httpHelper.httpPost(uri);
      return parseResponseBody(response, HTTP_OK);
    }

  private Tweet parseResponseBody(HttpResponse response,Integer expectedStatusCode)
  {
    Tweet tweet = null;

    int status = response.getStatusLine().getStatusCode();
    if (status != TwitterDao.HTTP_OK) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println(("Response has no entity"));
      }
      throw new RuntimeException("unexpected HTTP status:" + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    //convert response entity to str
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to string", e);
    }

    //Convert JSON string to Tweet object
    try {
      tweet = JsonParser.toObjectFromJson(jsonStr, Tweet.class);
      System.out.println(tweet);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert Json str to object", e);
    }
    return tweet;
  }


  @Override
  public Tweet findById(String o) { URI uri = null;
    try {
      uri = new URI(API_BASE_URI + SHOW_PATH +QUERY_SYM + "id"+ EQUAL + o);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Couldn't create new URI object", e);
    }
    // PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);


  }

  @Override
  public Tweet deleteById(String m) {
    URI uri = null;
    try {
      uri = new URI(API_BASE_URI + DELETE_PATH + "/" + m + ".json");
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Couldn't create new URI object", e);
    }
   // PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);


  }
}
**/