package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.Model.Coordinates;
import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

  public final String COORD_SEP =":";
  public final String COMMA=",";
  private Service service;
  @Autowired
  public TwitterController(Service service)
  {this.service=service;}
  @Override
  public Tweet postTweet(String[] args) {
    if (args.length!=3) {
      throw new IllegalArgumentException("USAGE : cLIApp ");
    }
      String tweet_txt = args[1];
      String coord =args[2];
      String[] coordArray = coord.split(COORD_SEP);
      if(coordArray.length != 2 || StringUtils.isEmpty(tweet_txt))
      {
        throw new IllegalArgumentException("Incorrect Location information entered");
      }
      Double lat = null;
      Double lon = null;
      try{
        lat = Double.parseDouble(coordArray[0]);
        lon =Double.parseDouble(coordArray[1]);

      }
      catch (Exception e )
      {
        throw new IllegalArgumentException("Location is not right format",e);

      }
      Tweet postTweet = TweetBuilder.buildTweet(tweet_txt,lon,lat);
      return service.postTweet(postTweet);


  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 2){
      throw new IllegalArgumentException("Missing Argument");

    }
  String id_str = args[1];
  String[] field = Arrays.copyOfRange(args,2,args.length);
  return service.showTweet(id_str,field);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
   if (args.length<2)
   {
     throw new IllegalArgumentException("Usage: TwitterCLIApp delete tweet_id1 [tweet_id2 ...]");
   }
   String[] ids = Arrays.copyOfRange(args,1,args.length);
   return service.deleteTweets(ids);
  }
}
