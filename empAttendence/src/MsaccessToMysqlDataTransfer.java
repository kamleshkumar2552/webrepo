import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MsaccessToMysqlDataTransfer
{
  public static Properties properties;
  
  public MsaccessToMysqlDataTransfer() {}
  
  public static void main(String[] args)
  {
	boolean errorFlag=false;
    String currentTime = null;
    String errorFile=null;
	String oldTime=null;
	String database=null;
	Connection conn=null;
	Statement s=null;
	ResultSet rs = null;
	Connection con = null;
	Statement stmt =null;
	String tableName =null;
	
    int count = 0;
    try {
      properties = new Properties();
      String home = System.getProperty("user.home");
      String propFile = "application.properties";
      if (new java.io.File(String.valueOf(home) + "/." + propFile).exists()) {
        java.io.FileInputStream inputStream = new java.io.FileInputStream(new java.io.File(String.valueOf(home) + "/." + propFile));
        properties.load(inputStream);
        inputStream.close();
      }
      errorFile = (String)properties.get("errorFile");
      String dbname = (String)properties.get("dbname");
      tableName = (String)properties.get("tablename");
      String username = (String)properties.get("username");
      String password = (String)properties.get("password");
      String accessdbname = (String)properties.get("accessDbname");
      String accesspwd = (String)properties.get("accesspwd");
      String fileName = (String)properties.get("fileName");
      SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
      Calendar calendar = Calendar.getInstance();
      currentTime = df.format(calendar.getTime());
      
      calendar.add(11, -2);
       oldTime = df.format(calendar.getTime());
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("old time ===" + oldTime);
      System.out.println("currentTime time ===" + currentTime);
      


       database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + fileName;
       conn = java.sql.DriverManager.getConnection(database, accessdbname, accesspwd);
       s = conn.createStatement();
      
      String selTable = "SELECT * FROM  Trans  WHERE Dt BETWEEN #" + oldTime + "# AND #" + currentTime + "#";
      


      s.execute(selTable);
       rs = s.getResultSet();
      System.out.println("---connecting to and getting data from msaccess--" + rs);
       con = java.sql.DriverManager.getConnection(dbname, username, password);
      System.out.println("---connecting to mysql--" + con);
       stmt = con.createStatement();
      


      while ((rs != null) && (rs.next())) {
        String logout = "";
        String loin = "";
        String test = "0";
        String inout = rs.getString(6);
        

        if (inout.equals("0")) {
          loin = rs.getString(5);
        } else {
          logout = rs.getString(5);
        }
        String addRow = "INSERT INTO  " + tableName + "  VALUES ( '" + rs.getString(3) + "','" + rs.getString(14) + "','" + loin + "','" + logout + "','" + rs.getString(13) + "','" + rs.getString(4) + "','" + rs.getString(15) + "')";
        stmt.execute(addRow);
        count++;
      }
      System.out.println("-------------------success   " + currentTime + "  " + count + "record  inserted---------------------------");
    }
    catch (SQLException ex) {
      errorFlag=true;
      ex.printStackTrace();
      System.out.println("---------------------------------fail   " + currentTime + "---------------------------------------------");
      String[] to = { "avinash.reddy@terralogic.com", "prasanna.rajesh@terralogic.com", "mahendra.sah@terralogic.com", "lam.nguyen@terralogic.com", "laiju.gangadharan@terralogic.com" };
      String msg = "Network present - but connectivity problem between Colo & attendance server\nNote : This is auto-generated alert from finger print DB machine, Please contact internal-IT for issue resolution.";
      sendFromGMail(to, "Issue Alert raised in Finger Print DB", msg);
    }
    catch (Exception ex) {
      errorFlag=true;
      ex.printStackTrace();
      System.out.println("---------------------------------fail   " + currentTime + "---------------------------------------------");
      String[] to = { "mahendra.sah@terralogic.com" };
      sendFromGMail(to, "Issue Alert raised in Finger Print DB", "Network present but problem with data");
    }
    if(errorFlag)
    {
    	createAndWrite(errorFile,oldTime,currentTime);
    }
    else
    {
    	updateFailRecords(errorFile,s,rs,tableName,stmt);
    }
    

    if (count == 0)
    {
      java.util.Date ctimes = new java.util.Date();
      int hour = ctimes.getHours();
      if ((hour > 9) && (hour < 12))
      {

        String[] to = { "avinash.reddy@terralogic.com", "prasanna.rajesh@terralogic.com", "mahendra.sah@terralogic.com" };
        String msg = "Data is not updating in 'c:/program Files(x86)/NetXsControl/netXs.mdb file' (MsAccess DB) Please check it.\nNote : This is auto-generated alert from finger print DB machine";
        sendFromGMail(to, "Issue Alert raised in Finger Print DB", msg);
      }
    }
  }
  


  public static void sendFromGMail(String[] to, String userMessage, String subject)
  {
    Properties props = System.getProperties();
    


    String host = "smtp.gmail.com";
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", "terralogic.software");
    props.put("mail.smtp.password", "terralogic123");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.debug", "true");
    props.put("mail.store.protocol", "imap");
    
    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);
    try
    {
      message.setFrom(new InternetAddress("terralogic.software"));
      InternetAddress[] toAddress = new InternetAddress[to.length];
      
      for (int i = 0; i < to.length; i++) {
        toAddress[i] = new InternetAddress(to[i]);
      }
      for (int i = 0; i < toAddress.length; i++) {
        message.addRecipient(javax.mail.Message.RecipientType.TO, toAddress[i]);
      }
      message.setSubject(userMessage);
      String body = subject;
      message.setText(body);
      Transport transport = session.getTransport("smtp");
      transport.connect(host, "terralogic.software", "terralogic123");
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    }
    catch (javax.mail.internet.AddressException ae) {
      ae.printStackTrace();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }
  
  private static void createAndWrite(String filename,String oldTime,String currentTime) {
		try
		{
			FileWriter writer = new FileWriter(filename, true);
			writer.write(oldTime+","+currentTime);
			writer.write("\r\n");
			writer.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
  
  private static void updateFailRecords(String filename,Statement s,ResultSet rs,String tableName,Statement stmt) {

		try
		{

			int count=0;
			BufferedReader rd = null;

			rd = new BufferedReader(new FileReader(new File(filename)));

			String inputLine = null;

			while((inputLine = rd.readLine()) != null)
			{
				System.out.println(inputLine);
				String str[]=inputLine.split(",");
				String d1=str[0];
				String d2=str[1];
				String selTable = "SELECT * FROM  Trans  WHERE Dt BETWEEN #" + d1 + "# AND #" + d2 + "#";
			    s.execute(selTable);
			    rs = s.getResultSet();
			    while ((rs != null) && (rs.next())) {
			        String logout = "";
			        String loin = "";
			        String inout = rs.getString(6);			        

			        if (inout.equals("0")) {
			          loin = rs.getString(5);
			        } else {
			          logout = rs.getString(5);
			        }
			        String addRow = "INSERT INTO  " + tableName + "  VALUES ( '" + rs.getString(3) + "','" + rs.getString(14) + "','" + loin + "','" + logout + "','" + rs.getString(13) + "','" + rs.getString(4) + "','" + rs.getString(15) + "')";
			        stmt.execute(addRow);
			        count++;
			      }
				

			}
			System.out.println("#note  "+count+"  old record inserted ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
				{
					PrintWriter writer = new PrintWriter(filename);
					writer.print("");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}
	}
  
}