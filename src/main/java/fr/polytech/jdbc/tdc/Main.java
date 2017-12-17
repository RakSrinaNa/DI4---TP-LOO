package fr.polytech.jdbc.tdc;

import javax.swing.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 20/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-20
 */
public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		
		User loggedUser = controller.connect();
		
		if(loggedUser == null)
		{
			System.out.println("Not logged in");
			controller.close();
			System.exit(2);
		}
		
		int choice = -1;
		Directory currentDirectory = controller.getRootDirectory();
		if(currentDirectory == null)
		{
			System.out.println("Error getting root");
			System.exit(2);
		}
		try
		{
			do
			{
				switch(choice)
				{
					case 1:
						File filee = controller.getFile(currentDirectory, JOptionPane.showInputDialog(null, "Name of the file?"));
						System.out.println(filee);
						break;
					case 2:
						Directory dir = controller.getDirectory(currentDirectory, JOptionPane.showInputDialog(null, "Name of the directory?"));
						if(dir == null)
							System.out.println("Directory nout found");
						else
							currentDirectory = dir;
						break;
					case 3:
						File file = controller.getFile(currentDirectory, JOptionPane.showInputDialog(null, "Name of the file?"));
						if(file == null)
							System.out.println("File doesn't exist");
						else
							System.out.println("Downloading file: " + file.getPath());
						break;
					case 4:
						if(currentDirectory.getParent() != null)
							currentDirectory = currentDirectory.getParent();
				}
				System.out.println();
				System.out.println(currentDirectory);
			}
			while((choice = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Get infos on file.\n2.Open directory.\n3. Download File\n4. Parent directory\n99. Exit"))) != 99);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid choice, exiting...");
		}
		
		controller.close();
	}
}
