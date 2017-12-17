package fr.polytech.jdbc.tdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 19/09/2017.
 *
 * @author Thomas Couchoud, GRODECOEUR Clément
 * @since 2017-09-19
 */
public class JDBCConnection
{
	private Connection databaseConnection;
	
	/**
	 * Create a connection to the database.
	 *
	 * @param databaseURL The database URL.
	 * @param port The database port.
	 * @param dbName The database name.
	 * @param dbUser The database user.
	 * @param dbPass The database password.
	 */
	public JDBCConnection(String databaseURL, int port, String dbName, String dbUser, String dbPass)
	{
		databaseConnection = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			databaseConnection = DriverManager.getConnection("jdbc:mysql://" + databaseURL + ":" + port + "/" + dbName, dbUser, dbPass);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Error, driver not found!");
			System.exit(2);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			System.exit(2);
		}
	}
	
	public Connection getDatabaseConnection()
	{
		return databaseConnection;
	}
	
	public void close()
	{
		try
		{
			if(databaseConnection != null)
				databaseConnection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendUpdate(String requests) throws SQLException
	{
		Statement statement = getDatabaseConnection().createStatement();
		for(String request : requests.split(";"))
			if(!request.equals(""))
			{
				System.out.println("Sending update request: " + request);
				statement.executeUpdate(request);
			}
		statement.close();
	}
}
