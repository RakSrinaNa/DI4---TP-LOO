package fr.polytech.jdbc.td4;

import fr.polytech.jdbc.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BouquinsController
{
	private JDBCConnection c;
	private ObservableList<Book> ob = FXCollections.observableArrayList();
	
	public BouquinsController()
	{
		c = new JDBCConnection("localhost", 3306, "bouquins", "root", "");
		ob.addListener((ListChangeListener<Book>) c -> {
			for(Book b : c.getAddedSubList())
			{
				b.sujetProperty().addListener((obs, oldV, newV) -> sendUpdates("UPDATE LIVRE SET SUJET = " + newV));
				b.descriptionProperty().addListener((obs, oldV, newV) -> sendUpdates("UPDATE LIVRE SET DESCRIPTION = " + newV));
				b.nCopiesProperty().addListener((obs, oldV, newV) -> sendUpdates("UPDATE LIVRE SET COPIES = " + newV));
			}
		});
	}
	
	public void stop()
	{
		c.close();
	}
	
	private void sendUpdates(String queries)
	{
		try
		{
			Statement s = c.getDatabaseConnection().createStatement();
			for(String q : queries.split(";"))
			{
				if(!q.equals(""))
				{
					s.executeUpdate(q + ";");
				}
			}
			s.close();
		}
		catch(SQLException e)
		{
			System.out.println("EEEEEEH non : " + e.getMessage());
		}
	}
	
	public Book getBook(int isbn)
	{
		try
		{
			PreparedStatement ps = c.getDatabaseConnection().prepareStatement("SELECT * FROM LIVRE WHERE ISBN = ?;");
			ps.setInt(1, isbn);
			ResultSet result = ps.executeQuery();
			ArrayList<Book> l = new ArrayList<>();
			while(result.next())
			{
				l.add(new Book(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));
			}
			ps.close();
			if(l.isEmpty())
				return null;
			return l.get(0);
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Book> getAllBooks()
	{
		try
		{
			PreparedStatement ps = c.getDatabaseConnection().prepareStatement("SELECT * FROM LIVRE;");
			ResultSet result = ps.executeQuery();
			ArrayList<Book> l = new ArrayList<>();
			while(result.next())
			{
				l.add(new Book(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));
			}
			ps.close();
			return l;
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
		}
		return null;
	}
	
	public ObservableList<Book> getBookList()
	{
		return ob;
	}
}
