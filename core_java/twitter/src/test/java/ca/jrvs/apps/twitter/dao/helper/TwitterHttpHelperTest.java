package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest {



  @Test
  public void httpPost() throws Exception{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper newHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    HttpResponse response = newHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Hello_World_from_Java"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws Exception{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper newHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    HttpResponse getrequest = newHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=1445414787018629123"));
    System.out.println(EntityUtils.toString(getrequest.getEntity()));
  }
  // System.out.println(EntityUtils.toString(response.getEntity()));

}
