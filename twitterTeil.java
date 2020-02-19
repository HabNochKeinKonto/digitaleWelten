package dwe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class twitterTeil{ 
	 static Twitter twitter = TwitterFactory.getSingleton();
	 static RequestToken requestToken;
	 static AccessToken accessToken = null;
public static void main(String args[]) throws Exception{
	System.out.println(gebeURL());
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 String pin = br.readLine();
	 pinEingeben(pin);
	 tweetSenden("abc");
	 System.out.println(likesDerLetztenTweets());
	 for(String s : zeigeStartseite()) {
		 System.out.println(s);
	 }
	 for(String s : letzteTweets()) {
		 System.out.println(s);
	 }
                    
    }

public static String gebeURL () throws TwitterException {
	 twitter.setOAuthConsumer("", "");
	 requestToken = twitter.getOAuthRequestToken();
	 return requestToken.getAuthorizationURL();
}
    
public static  void pinEingeben(String pin) {
	 try{
         if(pin.length() > 0){
           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
           
           //erfolgreich
         }else{
           accessToken = twitter.getOAuthAccessToken();
         }
      } catch (TwitterException te) {
        if(401 == te.getStatusCode()){
          System.out.println("Unable to get the access token.");
        }else{
          te.printStackTrace();
        }
      }
    }

public static ArrayList<String> zeigeStartseite() throws TwitterException{
	ArrayList<String> ausgabe = new ArrayList();
	List<Status> statuses = twitter.getHomeTimeline();
	   for (Status status : statuses) {
	        ausgabe.add("Tweet geschrieben von " + status.getUser().getName() + "Inhalt : " +
	                           status.getText());
	    }
	   return ausgabe;
}
public static ArrayList<String> letzteTweets() throws TwitterException{
	ArrayList<String> ausgabe = new ArrayList();
	List<Status> statuses = twitter.getUserTimeline();
	   for (Status status : statuses) {
	        ausgabe.add("Tweet geschrieben von " + status.getUser().getName() + "Inhalt :" +
	                           status.getText());
	    }
	   return ausgabe;
}

public static String likesDerLetztenTweets() throws TwitterException{
	
	List<Status> statuses = twitter.getUserTimeline();
	int likes = 0;
	int tweets = 0;
	   for (Status status : statuses) {
	        likes = likes + status.getFavoriteCount();
	        tweets++;
	    }
	   return ("Die letzten " + tweets + " Tweets bekamen insgesamt " + likes + " likes. Das sind " + (likes/tweets) +  " pro Tweet");
}
public static String retweetsDerLetztenTweets() throws TwitterException{
	
	List<Status> statuses = twitter.getUserTimeline();
	int retweets = 0;
	int tweets = 0;
	   for (Status status : statuses) {
	        retweets = retweets + status.getRetweetCount();
	        tweets++;
	    }
	   return ("Die letzten " + tweets + "bekamen insgesamt " + retweets + " retweets. Das sind " + (retweets/tweets) +  " pro Tweet");
}






public static boolean tweetSenden (String text) {
	Status status;
	try {
	status = twitter.updateStatus(text);
	return true;
	}
	catch(Exception e) {
		return false;
	}
	
}


	
}


