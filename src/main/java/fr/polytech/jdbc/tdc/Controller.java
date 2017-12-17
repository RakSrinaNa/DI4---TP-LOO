package fr.polytech.jdbc.tdc;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 20/10/2017.
 *
 * @author Thomas Couchoud, GRODECOEUR Cleament
 * @since 2017-10-20
 */
public class Controller
{
	private final JDBCConnection connection;
	
	/**
	 * Creates a model.
	 */
	public Controller()
	{
		this.connection = new JDBCConnection("localhost", 3306, "COUCHOUD_GRODECOEUR_CC", "root", "");
	}
	
	/**
	 * Close the connection when done.
	 */
	public void close()
	{
		connection.close();
	}
	
	/**
	 * Get a user from the database with its username and password.
	 *
	 * @param username The username.
	 * @param password The password.
	 * @return The user if exists, null otherwise.
	 * @throws SQLException If an error occurred.
	 */
	public User getUser(String username, String password) throws SQLException
	{
		PreparedStatement ps = connection.getDatabaseConnection().prepareStatement("SELECT ID, Username FROM User WHERE Username = ? AND Password = ?");
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		User user = null;
		if(rs.next())
			user = new User(rs.getInt("ID"), rs.getString("Username"));
		ps.close();
		return user;
	}
	
	/**
	 * Connect a user to the database.
	 *
	 * @return The connected user.
	 */
	public User connect()
	{
		try
		{
			switch(JOptionPane.showInputDialog(null, "Do you have an account? Y/N"))
			{
				case "N":
					String username = JOptionPane.showInputDialog(null, "Username?");
					String password = JOptionPane.showInputDialog(null, "Password?");
					return addUser(username, password);
				case "Y":
					User user = getUser(JOptionPane.showInputDialog(null, "Username?"), JOptionPane.showInputDialog(null, "Password?"));
					if(user == null)
						throw new IllegalStateException("Invalid user");
					return user;
				default:
					throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Invalid choice");
			close();
			System.exit(2);
		}
		catch(IllegalStateException e)
		{
			System.out.println(e.getMessage());
			close();
			System.exit(2);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			close();
			System.exit(2);
		}
		return null;
	}
	
	/**
	 * Add a user into the database.
	 *
	 * @param username The username.
	 * @param password The password.
	 * @return The created user.
	 * @throws SQLException If an error occurred.
	 * @throws IllegalStateException If the user already exists.
	 */
	private User addUser(String username, String password) throws SQLException, IllegalStateException
	{
		if(getUser(username, password) != null)
			throw new IllegalStateException("User already exists");
		PreparedStatement ps = connection.getDatabaseConnection().prepareStatement("INSERT INTO user (Username, Password) VALUES (?,?);");
		ps.setString(1, username);
		ps.setString(2, password);
		ps.executeUpdate();
		ps.close();
		return getUser(username, password);
	}
	
	/**
	 * Get a file from the database.
	 *
	 * @param currentDirectory The directory to get it from.
	 * @param name The name of the file.
	 * @return The desired file, null if the file doesn't exists.
	 */
	public File getFile(Directory currentDirectory, String name)
	{
		return currentDirectory.getFiles().stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
	}
	/**
	 * Get a directory from the database.
	 *
	 * @param currentDirectory The current directory.
	 * @param name The directory to open.
	 * @return The opened directory.
	 */
	public Directory getDirectory(Directory currentDirectory, String name)
	{
		Tuple<Integer, String> dirInfo = currentDirectory.getDirectories().stream().filter(d -> d.getVal().equals(name)).findAny().orElse(null);
		if(dirInfo == null)
			return null;
		Directory dir = null;
		PreparedStatement ps;
		try
		{
			ps = connection.getDatabaseConnection().prepareStatement("SELECT ID, Type, Name, Size FROM file WHERE Parent = ?;");
			ps.setInt(1, dirInfo.getKey());
			ResultSet rs = ps.executeQuery();
			ArrayList<File> files = new ArrayList<>();
			ArrayList<Tuple<Integer, String>> directories = new ArrayList<>();
			dir = new Directory(dirInfo.getKey(), dirInfo.getVal(), currentDirectory, files, directories);
			
			while(rs.next())
			{
				switch(rs.getString("Type"))
				{
					case "D":
						directories.add(new Tuple<>(rs.getInt("ID"), rs.getString("Name")));
						break;
					case "F":
						files.add(new File(rs.getInt("ID"), rs.getString("Name"), dir, rs.getInt("Size")));
						break;
				}
			}
			
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return dir;
	}
	
	/**
	 * Get the root directory.
	 *
	 * @return The root directory. Null if an error occurred.
	 */
	public Directory getRootDirectory()
	{
		try
		{
			Statement ps = connection.getDatabaseConnection().createStatement();
			ResultSet rs = ps.executeQuery("SELECT ID, Type, Name, Size FROM file WHERE Parent IS NULL;");
			
			ArrayList<File> files = new ArrayList<>();
			ArrayList<Tuple<Integer, String>> directories = new ArrayList<>();
			Directory directory = new Directory(-1, "", null, files, directories);
			
			while(rs.next())
			{
				switch(rs.getString("Type"))
				{
					case "D":
						directories.add(new Tuple<>(rs.getInt("ID"), rs.getString("Name")));
						break;
					case "F":
						files.add(new File(rs.getInt("ID"), rs.getString("Name"), directory, rs.getInt("Size")));
						break;
				}
			}
			ps.close();
			return directory;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
