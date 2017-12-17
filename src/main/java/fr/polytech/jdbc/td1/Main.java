package fr.polytech.jdbc.td1;

import sun.tools.java.Type;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 15/09/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-09-15
 */
public class Main
{
	private static final String DATABASE_URL = "localhost";
	private static final int PORT = 3306;
	private static final String DB_NAME = "LOO";
	private static final String DB_USER = "LOO";
	private static final String DB_PASSWORD = "";
	
	public static void main(String[] args)
	{
		Connection databaseConnection = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			databaseConnection = DriverManager.getConnection("jdbc:mysql://" + DATABASE_URL + ":" + PORT + "/" + DB_NAME, DB_USER, DB_PASSWORD);
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
		
		try
		{
			sendUpdate(databaseConnection.createStatement(),
					"CREATE TABLE IF NOT EXISTS `Catalogue`(`Fournisseur` INT(11) NOT NULL, `Produit` INT(11) NOT NULL, `Prix` FLOAT NOT NULL, PRIMARY KEY (Fournisseur, Produit));" +
							"CREATE TABLE IF NOT EXISTS `Fournisseurs`(`CodeFournisseur` INT(11) NOT NULL, `Nom` VARCHAR(20) NOT NULL, `Address` VARCHAR(40) NOT NULL, `Ville` VARCHAR(20) NOT NULL, PRIMARY KEY (CodeFournisseur));" +
							"CREATE TABLE IF NOT EXISTS `Produits`(`CodeProduit` INT(11) NOT NULL, `Type` VARCHAR(10) NOT NULL, `Marque` VARCHAR(20) NOT NULL, `Modele` VARCHAR(20) NOT NULL, PRIMARY KEY (CodeProduit));" +
							"TRUNCATE Catalogue;" +
							"TRUNCATE Fournisseurs;" +
							"TRUNCATE Produits;");
			
			sendUpdate(databaseConnection.createStatement(),
					"INSERT INTO Fournisseurs VALUES " +
								"(1, 'Dubois', 'Rue Paul Bert', 'Paris')," +
								"(2, 'Dupont', 'Avenue Avenue', 'Toulouse')," +
								"(10, 'Cuissart', 'Rue Vaugiraud', 'Lyon')" +
							";");
			
			sendUpdate(databaseConnection.createStatement(), "DELETE FROM Catalogue WHERE Fournisseur IN (SELECT CodeFournisseur FROM Fournisseurs WHERE Nom='peugoet');");
			
			sendUpdate(databaseConnection.createStatement(),
					"CREATE TABLE IF NOT EXISTS Citoyen(NSS INT(15) NOT NULL, Nom VARCHAR(20) NOT NULL, Prenom VARCHAR(20) NOT NULL , Sex CHAR NOT NULL, Conjoint INT(15), Foyer INT, PRIMARY KEY (NSS));" +
							"CREATE TABLE IF NOT EXISTS Foyer(Code INT NOT NULL, Pere INT(15), Mere INT(15), Address VARCHAR(40) NOT NULL, PRIMARY KEY (Code));" +
							"TRUNCATE Citoyen;" +
							"TRUNCATE Foyer;"
			);
			
			sendUpdate(databaseConnection.createStatement(),
					"INSERT INTO Citoyen VALUES" +
								"(1, 'A', 'B', 'M', 2, 1)," +
								"(2, 'A', 'C', 'F', 1, 1)," +
								"(3, 'B', 'D', 'T', NULL, 2)" +
							";" +
							"INSERT INTO Foyer VALUES" +
								"(1, 1, 2, 'Rue de la quenelle')," +
								"(2, NULL, 3, 'Rue du jambon beurre')" +
							";"
			);
			
			Scanner sc = new Scanner(System.in);
			
			PreparedStatement preparedStatement = databaseConnection.prepareStatement("INSERT INTO Citoyen VALUES(?,?,?,?,?,?);");
			System.out.print("NSS: ");
			preparedStatement.setInt(1, sc.nextInt());
			sc.nextLine();
			System.out.print("Nom: ");
			preparedStatement.setString(2, sc.nextLine());
			System.out.print("Prenom: ");
			preparedStatement.setString(3, sc.nextLine());
			System.out.print("Sexe: ");
			preparedStatement.setString(4, sc.nextLine());
			System.out.print("Conjoint NSS: ");
			try
			{
				preparedStatement.setInt(5, sc.nextInt());
			}
			catch(NoSuchElementException | IllegalStateException e)
			{
				preparedStatement.setNull(5, Type.INT);
				sc.nextLine();
			}
			System.out.print("Foyer ID: ");
			try
			{
				preparedStatement.setInt(6, sc.nextInt());
			}
			catch(NoSuchElementException | IllegalStateException e)
			{
				preparedStatement.setNull(6, Type.INT);
			}
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			sc.close();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(databaseConnection != null && !databaseConnection.isClosed())
					databaseConnection.close();
			}
			catch(SQLException ignored)
			{
			}
		}
	}
	
	private static void sendUpdate(Statement statement, String requests) throws SQLException
	{
		for(String request : requests.split(";"))
			if(!request.equals(""))
			{
				System.out.println("Sending update request: " + request);
				statement.executeUpdate(request);
			}
		statement.close();
	}
}
