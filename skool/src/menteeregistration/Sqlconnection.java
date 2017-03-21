package menteeregistration;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class Sqlconnection {
	private static final String driverClass;
	private static final String url;
	private static final String username;
    private static final String password;
	static
	{
		Properties pr=new Properties();
		FileReader in=null;
		try
		{
			in=new FileReader("/home/kamalesh/constants.properties");
			pr.load(in);
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(in!=null)
				{
					in.close();
					in=null;
				}
			}catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
		driverClass=pr.getProperty("driverClass");
		url=pr.getProperty("url");
		username =pr.getProperty("username");
		password=pr.getProperty("password");
	}
	static
	{
		try
		{
			Class.forName(driverClass);
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	public static Connection getConnection()throws SQLException
	{
		return DriverManager.getConnection(url,username,password);
		 
	}
	public static void closeAll(ResultSet rs,Statement stmt,Connection con)
	{
		 try
		  {
			  if(rs!=null)
			  {
				  rs.close();
				  rs=null;
			  }
		  }catch(SQLException ex)
		  {
			  ex.printStackTrace();
		  }
		 try
		  {
			  if(stmt!=null)
			  {
				  stmt.close();
				  stmt=null;
			  }
		  }catch(SQLException ex)
		  {
			  ex.printStackTrace();
		  }
		 try
		  {
			  if(con!=null)
			  {
				  con.close();
				  con=null;
			  }
		  }catch(SQLException ex)
		  {
			  ex.printStackTrace();
		  }
	}
}
