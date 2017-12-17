package fr.polytech.jdbc.td2;

import fr.polytech.jdbc.JDBCConnection;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 19/09/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-09-19
 */
public class Main
{
	public static void main(String[] args)
	{
		JDBCConnection connection = new JDBCConnection("localhost", 3306, "LOO", "LOO", "");
		try
		{
			connection.sendUpdate("CREATE TABLE IF NOT EXISTS Auteurs(Code INT, Nom VARCHAR(100) NOT NULL, Prenom VARCHAR(20) NOT NULL, PRIMARY KEY(Code)); CREATE TABLE IF NOT EXISTS Livres(ISBN INT, Titre VARCHAR(20) NOT NULL, URLImage VARCHAR(100), Auteur INT NOT NULL, Editeur INT NOT NULL, PRIMARY KEY (ISBN)); CREATE TABLE IF NOT EXISTS Editeurs(Code INT, Nom VARCHAR(20) NOT NULL, Address VARCHAR(40) NOT NULL, Ville VARCHAR(20) NOT NULL, PRIMARY KEY (Code));");
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
		try
		{
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Livres LEFT JOIN Editeurs ON Livres.Editeur=Editeurs.Code LEFT JOIN Auteurs ON Auteurs.Code=Livres.Auteur;");
			while(result.next())
			{
				System.out.format("ISBN: %d, Titre: %s, Image: %s, Auteur: %s %s, Editeur: %s à l'adresse %s %s\n", result.getInt("ISBN"), result.getString("Titre"), result.getString("URLImage"), result.getString("Auteurs.Nom"), result.getString("Auteurs.Prenom"), result.getString("Editeurs.Nom"), result.getString("Editeurs.Address"), result.getString("Editeurs.Ville"));
			}
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
		Scanner sc = new Scanner(System.in);
		try
		{
			PreparedStatement statement = connection.getDatabaseConnection().prepareStatement("SELECT * FROM Livres LEFT JOIN Editeurs ON Livres.Editeur=Editeurs.Code LEFT JOIN Auteurs ON Auteurs.Code=Livres.Auteur WHERE ISBN=?;");
			sc = new Scanner(System.in);
			System.out.print("ISBN? ");
			statement.setInt(1, Integer.parseInt(sc.nextLine()));
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				System.out.format("ISBN: %d, Titre: %s, Image: %s, Auteur: %s %s, Editeur: %s à l'adresse %s %s\n", result.getInt("ISBN"), result.getString("Titre"), result.getString("URLImage"), result.getString("Auteurs.Nom"), result.getString("Auteurs.Prenom"), result.getString("Editeurs.Nom"), result.getString("Editeurs.Address"), result.getString("Editeurs.Ville"));
			}
			statement.close();
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			sc.close();
			System.exit(2);
		}
		
		try
		{
			System.out.print("Editeur Nom? ");
			String temp = sc.nextLine();
			
			PreparedStatement getEditeurIDStatement = connection.getDatabaseConnection().prepareStatement("SELECT Code FROM Editeurs WHERE Nom=?;");
			getEditeurIDStatement.setString(1, temp);
			ResultSet result = getEditeurIDStatement.executeQuery();
			if(!result.next())
				throw new IllegalStateException("Editor 404");
			int editor = result.getInt("Code");
			getEditeurIDStatement.close();
			
			System.out.print("Auteur Nom Prenom? ");
			temp = sc.nextLine();
			
			PreparedStatement getAuteurIDStatement = connection.getDatabaseConnection().prepareStatement("SELECT Code FROM Auteurs WHERE CONCAT(Nom, ' ', Prenom)=?;");
			getAuteurIDStatement.setString(1, temp);
			result = getAuteurIDStatement.executeQuery();
			if(!result.next())
				throw new IllegalStateException("Auteur 404");
			int author = result.getInt("Code");
			getAuteurIDStatement.close();
			
			PreparedStatement insertLivreStatement = connection.getDatabaseConnection().prepareStatement("INSERT INTO Livres VALUES(?, ?, ?, ?, ?);");
			System.out.print("ISBN? ");
			insertLivreStatement.setInt(1, Integer.parseInt(sc.nextLine()));
			System.out.print("Titre? ");
			insertLivreStatement.setString(2, sc.nextLine());
			System.out.print("Image? ");
			temp = sc.nextLine();
			if(temp.equals(""))
				insertLivreStatement.setNull(3, Types.VARCHAR);
			else
				insertLivreStatement.setString(3, temp);
			insertLivreStatement.setInt(4, author);
			insertLivreStatement.setInt(5, editor);
			
			insertLivreStatement.executeUpdate();
			insertLivreStatement.close();
		}
		catch(Exception e)
		{
			System.out.println("ERROR " + e.getMessage());
			sc.close();
			connection.close();
			System.exit(2);
		}
		
		sc.close();
		connection.close();
	}
}
