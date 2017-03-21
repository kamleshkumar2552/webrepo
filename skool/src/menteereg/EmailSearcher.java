package menteereg;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

public class EmailSearcher {
    public void searchEmail(String host, String port, String userName,
            String password, final String keyword) throws IOException {
        Properties properties = new Properties();
 

        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", port);
 
       
        properties.setProperty("mail.imap.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port",
                String.valueOf(port));
 
        Session session = Session.getDefaultInstance(properties);
 
        try {
        
            Store store = session.getStore("imap");
            store.connect(userName, password);
 
   
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
 
          
            SearchTerm searchCondition = new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    try {
                        if (message.getSubject().equalsIgnoreCase(keyword)) {
                            return true;
                        }
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            };
 
          
            Message[] foundMessages = folderInbox.search(searchCondition);
 
            for (int i = 0; i < foundMessages.length; i++) {
                Message message = foundMessages[i];
                String subject = message.getSubject();
                System.out.println("Found message #" + i + ": " + subject);
                System.out.println("Text: " + message.getContent().toString());
                String s1="http://sandbox.skoolmentor.com/";
                s1=s1.concat(message.getContent().toString().substring(92,121));
                
               /* String s2=s1.substring(92,121);*/
                System.out.println("complecte url::" + s1);
                
            }
 
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        }
    }
 
  
    public static void main(String[] args) throws IOException {
        String host = "imap.gmail.com";
        String port = "993";
        String userName = "prklgc@gmail.com";
        String password = "foramazon";
        EmailSearcher searcher = new EmailSearcher();
        String keyword = "Confirm Account Activation";
        
        searcher.searchEmail(host, port, userName, password, keyword);
    }
 
}