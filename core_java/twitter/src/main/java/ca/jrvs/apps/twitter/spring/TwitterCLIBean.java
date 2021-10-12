package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.applet.Applet;
import java.io.IOException;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TwitterCLIBean {

  public static void main(String[] args) throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
    ApplicationContext context= new AnnotationConfigApplicationContext(TwitterCLIBean.class);
    TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
    app.run(args);
  }
@Bean
  public TwitterCLIApp twitterCLIApp(TwitterController twitterController)
{
  return new TwitterCLIApp(twitterController);
}

  @Bean
  public TwitterController twitterController(TwitterService twitterService)
  {
    return new TwitterController(twitterService);
  }
  @Bean
  public TwitterService twitterService(TwitterDao twitterDao)
  {
    return new TwitterService(twitterDao);
  }
  @Bean
  public TwitterDao twitterDao(HttpHelper httpHelper)
  {
    return new TwitterDao(httpHelper);
  }

  @Bean
  public HttpHelper Helper()
  {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    return new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
  }
}
