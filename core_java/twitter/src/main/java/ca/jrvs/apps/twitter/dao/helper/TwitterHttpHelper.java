package ca.jrvs.apps.twitter.dao.helper;

import ch.qos.logback.classic.BasicConfigurator;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

public class TwitterHttpHelper implements HttpHelper{
  private final OAuthConsumer consumer;
  private final HttpClient httpClient;

 /** public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret){
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = new DefaultHttpClient();
  }
  **/
 public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
     String tokenSecret) {
   //Use default logger config
   //BasicConfigurator.configure();
   consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
   consumer.setTokenWithSecret(accessToken, tokenSecret);
   httpClient = HttpClientBuilder.create().build();
 }

  public static void main(String[] args) throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper newHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    HttpResponse response = newHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=hellojava"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
  @Override
  public HttpResponse httpPost(URI uri) {
    try{
      return executeHttpRequest(HttpMethod.POST,uri,null);

    }
    catch(OAuthException | IOException e)
    {
      throw new RuntimeException("failed to execute",e );
    }

  }
  private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {
    if (method == HttpMethod.GET) {
      HttpGet getRequest = new HttpGet(uri);
      consumer.sign(getRequest);
      return httpClient.execute((getRequest));
    } else if (method == HttpMethod.POST) {
      HttpPost postRequest = new HttpPost(uri);
      consumer.sign(postRequest);
      return httpClient.execute((postRequest));
    } else {
      throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    try

    {
      return executeHttpRequest(HttpMethod.GET,uri,null);

    }
    catch(OAuthException | IOException e)
    {
      throw new RuntimeException("failed to execute",e );
    }

  }
}
