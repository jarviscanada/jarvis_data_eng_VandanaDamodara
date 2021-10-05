package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.Model.Tweet;
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
