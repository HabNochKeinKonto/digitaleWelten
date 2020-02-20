package dwe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class tt{ 
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
	 
	 ArrayList<hashtag> erg = verwendeteHashtags();
	 for(int i = 0;i<erg.size();i++) {
		 System.out.println(erg.get(i));
	 }
	 System.out.println(zw0und6Uhr());
	 System.out.println(zw6und12Uhr());
	 System.out.println(zw12und18Uhr());	 
	 System.out.println(zw18und0Uhr());
                    
    }


//gibt anmelde URL als string zurück
public static String gebeURL () throws TwitterException {
	 twitter.setOAuthConsumer("", "");
	 requestToken = twitter.getOAuthRequestToken();
	 return requestToken.getAuthorizationURL();
}

//pin eingeben (text nach verifier=)    
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

//gibt eine ArrayList mit dem Inhalt aller Tweets auf der Startseite zurück
public static ArrayList<String> zeigeStartseite() throws TwitterException{
	ArrayList<String> ausgabe = new ArrayList();
	List<Status> statuses = twitter.getHomeTimeline();
	   for (Status status : statuses) {
	        ausgabe.add(status.getText());
	    }
	   return ausgabe;
}
//gibt eine ArrayList mit den Autoren der tweets auf der startseite zurück
public static ArrayList<String> zeigeStartseiteAutoren() throws TwitterException{
	ArrayList<String> ausgabe = new ArrayList();
	List<Status> statuses = twitter.getHomeTimeline();
	   for (Status status : statuses) {
	        ausgabe.add(status.getUser().getName());
	    }
	   return ausgabe;
}

//gibt einen einzelnen tweets auf der startseite zurück
public static String zeigeTweetStartseite(int i) throws TwitterException{
	String ausgabe;
	List<Status> statuses = twitter.getHomeTimeline();
	
	   return statuses.get(i).getText();
	
	
}

//gibt autor von diesem tweets zurück
public static String zeigeTweetAutorStartseite(int i) throws TwitterException{
	String ausgabe;
	List<Status> statuses = twitter.getHomeTimeline();
	
	   return statuses.get(i).getUser().getName();
	

}

//gibt eine ArrayList mit den letzten tweets des nutzers aus (auch retweets)
public static ArrayList<String> letzteTweets() throws TwitterException{
	ArrayList<String> ausgabe = new ArrayList();
	List<Status> statuses = twitter.getUserTimeline();
	   for (Status status : statuses) {
	        ausgabe.add(status.getText());
	    }
	   return ausgabe;
}
//gibt einen bestimmten tweets zurück
public static String letzteTweet(int i) throws TwitterException{
	
	List<Status> statuses = twitter.getUserTimeline();
	   
	   
	   return statuses.get(i).getText();
	 
	   
}


//gibt die anzahl der likes der letzten tweets zurück (auch retweets)
public static int likesDerLetztenTweets() throws TwitterException{
	
	List<Status> statuses = twitter.getUserTimeline();
	int likes = 0;

	   for (Status status : statuses) {
	        likes = likes + status.getFavoriteCount();
	        
	    }
	   return likes;
}
//gibt die likes eines bestimmten tweets zurück
public static int likesvonTweet(int i) throws TwitterException{
	
	List<Status> statuses = twitter.getUserTimeline();
	
		return statuses.get(i).getFavoriteCount();
	

}
//gibt die anzahl der retweets der letzten tweets zurück (auch retweets)
public static int retweetsDerLetztenTweets() throws TwitterException{
	
	List<Status> statuses = twitter.getUserTimeline();
	int retweets = 0;
	
	   for (Status status : statuses) {
	        retweets = retweets + status.getRetweetCount();
	        
	    }
	   return retweets;
}
//gibt die anzahl der retweets auf einem bestimmten letzten tweet zurück

public static int retweetsvonTweet(int i) throws TwitterException{
	
	List<Status> statuses = twitter.getUserTimeline();
	
		return statuses.get(i).getRetweetCount();
	
	
}

//gibt eine liste mit den hashtags, die in den letzten tweets verwendet wurden zurück (auch in retweets)
public static ArrayList<hashtag> verwendeteHashtags() throws TwitterException{
	List<Status> statuses = twitter.getUserTimeline();
	ArrayList<String> texte = new ArrayList();
	ArrayList<String> woerter = new ArrayList();
	List<String> temp;
	ArrayList<String> tmp = new ArrayList();
	ArrayList<String> tmp2 = new ArrayList();
	liste l = new liste();
	 for (Status status : statuses) {
	        texte.add(status.getText());
	    }
	 for(int i = 0;i<texte.size();i++) {
	 
		 temp = Arrays.asList(texte.get(i).split(" ")); 
		 for(int j = 0;j<temp.size();j++) {
			 tmp.add(temp.get(j));
			 
			 
		 }
		 }
	 for(int i = 0;i<tmp.size();i++) {
		 
		 temp = Arrays.asList(tmp.get(i).split(",")); 
		 for(int j = 0;j<temp.size();j++) {
			 woerter.add(temp.get(j));
			 
			 
		 }
		 }
// for(int i = 0;i<tmp2.size();i++) {
//		 
//		 temp = Arrays.asList(tmp2.get(i).split(".")); 
//		 for(int j = 0;j<temp.size();j++) {
//			 woerter.add(temp.get(j));
//			 
//			 
//		 }
//		 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 for(int k = 0; k < woerter.size();k++) {
		 if(woerter.get(k).contains("#")) {
			 String s = woerter.get(k);
			 if(s.contains(".")) {
				if(s.indexOf('.')<s.indexOf('#')) {
					
					s = s.substring(s.indexOf('.')+1);
					//da punkt nicht enthalten ist, ist keine überprüfung nötig, ob der Punkt noch im String enthalten ist
					
					woerter.add(s);
					
				
					
				}
				else {
				l.gefunden(s.substring(s.indexOf('#'),s.indexOf('.')));
				s = s.substring(s.indexOf('.'));
				if(s.contains("#")) {
					
					woerter.add(s.substring(s.indexOf('.')+1));


				}
				
				
			 }}
			 else {
			 l.gefunden(s.substring(s.indexOf('#')));
			 }

		 }
	 }
	 	l.ende();
	 	return l.getListeX();
	 
}



//sendet einen tweet

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



//gibt die anzahl der tweets, die in dem entsprechenden zeitfenster geschrieben wurden zurück
public static int zw0und6Uhr() throws TwitterException {
	List<Status> statuses = twitter.getUserTimeline();
	int tweets = 0;

	   for (Status status : statuses) {
	       int i = status.getCreatedAt().getHours();
	       if(i >= 0 && i<6) {
	    	   tweets++;
	       }
	    }
	   return tweets;
	
}
public static int zw6und12Uhr() throws TwitterException {
	List<Status> statuses = twitter.getUserTimeline();
	int tweets = 0;

	   for (Status status : statuses) {
	       int i = status.getCreatedAt().getHours();
	       if(i >= 6 && i<12) {
	    	   tweets++;
	       }
	    }
	   return tweets;
	
}
public static int zw12und18Uhr() throws TwitterException {
	List<Status> statuses = twitter.getUserTimeline();
	int tweets = 0;

	   for (Status status : statuses) {
	       int i = status.getCreatedAt().getHours();
	       if(i >= 12 && i<18) {
	    	   tweets++;
	       }
	    }
	   return tweets;
	
}
public static int zw18und0Uhr() throws TwitterException {
	List<Status> statuses = twitter.getUserTimeline();
	int tweets = 0;

	   for (Status status : statuses) {
	       int i = status.getCreatedAt().getHours();
	       if(i >= 18 && i<24) {
	    	   tweets++;
	       }
	    }
	   return tweets;
	
}

	
}

