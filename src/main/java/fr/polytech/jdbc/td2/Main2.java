package fr.polytech.jdbc.td2;

import fr.polytech.jdbc.JDBCConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 19/09/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-09-19
 */
public class Main2
{
	public static void main(String[] args)
	{
		JDBCConnection connection = new JDBCConnection("localhost", 3306, "LOO", "LOO", "");
		
		try
		{
			HashMap<String, HashMap<Character, Integer>> grades = new HashMap<>();
			connection.sendUpdate("CREATE TABLE IF NOT EXISTS Notes(ID INT AUTO_INCREMENT, Nom VARCHAR(50) NOT NULL, Matiere VARCHAR(50) NOT NULL, Note CHAR NOT NULL, PRIMARY KEY (ID));");
			Statement statement = connection.getDatabaseConnection().createStatement();
			ResultSet lines = statement.executeQuery("SELECT * FROM Notes;");
			
			while(lines.next())
			{
				String matiere = lines.getString("Matiere");
				if(!grades.containsKey(matiere))
					grades.put(matiere, new HashMap<>());
				char note = lines.getString("Note").charAt(0);
				if(!grades.get(matiere).containsKey(note))
					grades.get(matiere).put(note, 0);
				grades.get(matiere).put(note, grades.get(matiere).get(note) + 1);
			}
			
			for(String matiere : grades.keySet())
			{
				System.out.println(matiere + ":");
				int max = grades.get(matiere).values().stream().mapToInt(i -> i).max().orElse(0);
				grades.get(matiere).keySet().stream().sorted().forEach(note -> {
					System.out.format("%c | %s\n", note, ponderate(grades.get(matiere).get(note), max, 50, '='));
				});
			}
			
			System.out.println(grades);
			
			statement.close();
		}
		catch(SQLException e)
		{
			System.out.println("ERROR " + e.getMessage());
			connection.close();
			System.exit(2);
		}
		
		connection.close();
	}
	
	private static String ponderate(int value, int maxValue, int maxChar, char c)
	{
		int count = (int) ((value / (double)maxValue) * maxChar);
		StringBuilder r = new StringBuilder();
		for(int i = 0; i< count; i++)
			r.append(c);
		return r.toString();
	}
}
