package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  private TwitterController twitterController;
  private final static String CONSUMER_KEY = System.getenv("consumerKey");
  private final static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private final static String ACCESS_TOKEN = System.getenv("accessToken");
  private final static String TOKEN_SECRET = System.getenv("tokenSecret");
@Autowired
  public TwitterCLIApp(TwitterController twitterController) {
    this.twitterController = twitterController;
  }

  public static void main(String[] args)
      throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {

    HttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN,
        TOKEN_SECRET);
    CrdDao dao = new TwitterDao(helper);
    Service service = new TwitterService(dao);
    TwitterController controller = new TwitterController(service);
    TwitterCLIApp cli = new TwitterCLIApp(controller);
    cli.run(args);
  }

  public void run(String[] args)
      throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
    if (args.length == 0) {
      throw new IllegalArgumentException("USAGE error");
    }
    switch (args[0].toLowerCase()) {
      case "post":
        Tweet tweet = twitterController.postTweet(args);
        System.out.println(JsonParser.toJson(tweet, true, false));
        break;
      case "show":
        Tweet tweetm = twitterController.showTweet(args);
        System.out.println(JsonParser.toJson(tweetm, true, false));
        break;
      case "delete":
        List<Tweet> tweets = twitterController.deleteTweet(args);
        //System.out.println(JsonParser.toJson(tweet, true, false));
        for (Tweet tw : tweets) {
          System.out.println(JsonParser.toJson(tw, true, false));
        }
        break;
      default:
        throw new IllegalArgumentException(
            "Incorrect argument passed. Valid actions: post | show | delete");
    }

  }

}