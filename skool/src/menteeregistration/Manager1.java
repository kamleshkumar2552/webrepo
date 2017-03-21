package menteeregistration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class Manager1 {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		try
		{
			con=Sqlconnection.getConnection();
			stmt=con.createStatement();
			//String s1="insert into tab3 values(2,'xyz')";
			String s1="select * from connections";
			stmt.executeQuery(s1);
			System.out.println(s1);
			System.out.println("done");
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			Sqlconnection.closeAll(null, stmt, con);
		}

	}

}
