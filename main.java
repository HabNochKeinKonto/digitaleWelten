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

public class main{ 
public static void main(String args[]) throws Exception{
    // The factory instance is re-useable and thread safe.
    Twitter twitter = TwitterFactory.getSingleton();
    twitter.setOAuthConsumer("lTjdElyQAS62rlVkCLhAMFbM6", "Tx7arTWxifILtym738dEKU0nUXy6fqU2YvnFuJ9UAkQVoJ6wfA");
    RequestToken requestToken = twitter.getOAuthRequestToken();
    AccessToken accessToken = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (null == accessToken) {
      
      System.out.println(requestToken.getAuthorizationURL());
      
      String pin = br.readLine();
      try{
         if(pin.length() > 0){
           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
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
    //persist to the accessToken for future reference.
    
    List<Status> statuses = twitter.getUserTimeline();

    for (Status status : statuses) {
    	
        System.out.println(status.getText());
    }
    System.out.println("Moechtest du Tweets schreiben (y/n)");
    {
    	Status status;
        
    	String input = br.readLine();
    	if(input.contentEquals("y")) {
    		for(int i = 0; i<6;i++) {
    			input =br.readLine();
    			status = twitter.updateStatus(input);
    		    
    			
    		}
    	}
    	else {
    		System.out.println("Dann eben nicht");
    	}
    }
                           
    }
    



}