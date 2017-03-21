package app1;


	
	import javax.mail.*;
	import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

import java.util.*;

	public class GmailFetch {

	  public static void main( String[] args ) throws Exception {

	    Session session = Session.getDefaultInstance(new Properties( ));
	    Store store = session.getStore("imaps");
	    store.connect("imap.googlemail.com", 993, "prklgc@gmail.com", "foramazon");
	    Folder inbox = store.getFolder( "INBOX" );
	    inbox.open( Folder.READ_ONLY );

	    
	    SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject().equalsIgnoreCase("Confirm Account Activation")) {
                        return true;
                    }
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
	
	    Message[] messages = inbox.search(searchCondition);   
	    Arrays.sort( messages, ( m1, m2 ) -> {
	      try {
	        return m2.getSentDate().compareTo( m1.getSentDate() );
	      } catch ( MessagingException e ) {
	        throw new RuntimeException( e );
	      }
	    } );

	 
            Message message = messages[0];
            String subject = message.getSubject();
            System.out.println("Subject::" + subject);
            System.out.println( 
      	          "sendDate: " + message.getSentDate()
      	          + " subject:" + message.getSubject() );
            
            
      
	    
	    
	    
	    
	  }
	}


