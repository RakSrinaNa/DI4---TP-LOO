package fr.polytech.jdbc.td3;

import fr.polytech.jdbc.JDBCConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main1 {
	public static void main(String args[]) {
		JDBCConnection connection = new JDBCConnection("localhost", 3306, "LOO", "root", "");

		try
		{
			String isbn = "3' OR 1 OR Titre = '";
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Livres LEFT JOIN Editeurs ON Livres.Editeur=Editeurs.Code LEFT JOIN Auteurs ON Auteurs.Code=Livres.Auteur WHERE ISBN = '" + isbn + "';");
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
		

		try
		{
			String isbn = "4' OR 1 OR Titre = '";
			PreparedStatement ps = connection.getDatabaseConnection().prepareStatement("SELECT * FROM Livres LEFT JOIN Editeurs ON Livres.Editeur=Editeurs.Code LEFT JOIN Auteurs ON Auteurs.Code=Livres.Auteur WHERE ISBN = ?;");
			ps.setString(1, isbn);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				System.out.format("ISBN: %d, Titre: %s, Image: %s, Auteur: %s %s, Editeur: %s à l'adresse %s %s\n", result.getInt("ISBN"), result.getString("Titre"), result.getString("URLImage"), result.getString("Auteurs.Nom"), result.getString("Auteurs.Prenom"), result.getString("Editeurs.Nom"), result.getString("Editeurs.Address"), result.getString("Editeurs.Ville"));
			}
			ps.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
	}
}
