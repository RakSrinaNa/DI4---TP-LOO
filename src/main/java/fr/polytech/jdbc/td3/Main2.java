package tpsgbd;

import fr.polytech.jdbc.JDBCConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main2 {
	public static void main(String args[]) {
		JDBCConnection connection = new JDBCConnection("localhost", 3306, "LOO", "root", "");

// ------------------------------- 1
		try
		{
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Album LEFT JOIN Chanteur ON Album.Chanteur = Chanteur.Nom LEFT JOIN Producteur ON Album.Producteur = Producteur.Nom WHERE Chanteur.Pays = 1 AND Producteur.Pays = 1;");
			while(result.next())
			{
				System.out.format("Album: %s, Chanteur: %s, Producteur: %s\n", result.getString("Album.Titre"), result.getString("Chanteur.Nom"), result.getString("Producteur.Nom"));
			}
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
// ------------------------------- 2
		try
		{
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Album LEFT JOIN Chanteur ON Album.Chanteur = Chanteur.Nom LEFT JOIN Producteur ON Album.Producteur = Producteur.Nom WHERE Chanteur.Pays = Producteur.Pays;");
			while(result.next())
			{
				System.out.format("Album: %s, Chanteur: %s, Producteur: %s\n", result.getString("Album.Titre"), result.getString("Chanteur.Nom"), result.getString("Producteur.Nom"));
			}
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
// ------------------------------- 3
		try
		{
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT SUM(Prix) AS Pri FROM Album;");
			while(result.next())
			{
				System.out.format("Prix total : %d\n", result.getInt("Pri"));
			}
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}

// ------------------------------- 4
		try
		{
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT SUM(Prix) AS Pri, Pays FROM Album LEFT JOIN Chanteur ON Album.Chanteur = Chanteur.Nom GROUP BY Chanteur.Pays;");
			while(result.next())
			{
				System.out.format("Prix de %s : %d\n", result.getString("Pays"), result.getInt("Pri"));
			}
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
// ------------------------------- 5
		try
		{
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT Chanteur.Nom FROM Chanteur LEFT JOIN Album ON Album.Chanteur = Chanteur.Nom LEFT JOIN Chanson ON Chanson.Album = Album.Idalbum WHERE Chanson.Duree = (SELECT MAX(Duree) AS Maxdur FROM Chanson);");
			while(result.next())
			{
				System.out.format("Chanson la plus longue par %s", result.getString("Chanteur.Nom"));
			}
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
		
// ------------------------------- 6
		try
		{
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT Album.Titre From Chanson LEFT JOIN Album ON Chanson.album = Album.Idalbum GROUP BY album HAVING SUM(Duree) >= ALL (SELECT SUM(Duree) From Chanson c GROUP BY c.album);");
			while(result.next())
			{
				System.out.format("Album le plus long : %s", result.getString("Titre"));
			}
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
	}
}
