package fr.polytech.hibernate.tp10;

import fr.polytech.hibernate.tp10.model.*;
import fr.polytech.hibernate.tp10.view.MainWindow;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 20/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-20
 */
public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		populate(controller);
		MainWindow mainWindow = new MainWindow(controller);
		mainWindow.setVisible(true);
	}
	
	private static void populate(Controller controller)
	{
		Client client1 = new Client("Jean Bon", "0123456789");
		controller.addClient(client1);
		controller.setClientAddress(client1, new Address("22 Rue de la chance", "Ville", "Poitou-Charentes", "12345", "France"));
		
		Container container1 = new Container(10, "verre");
		controller.addContainer(container1);
		
		Typology typology1 = new Typology("Gros", 100);
		controller.addTypology(typology1);
		
		GroundTypology groundTypology1 = new GroundTypology("Coulants", 2, "mousse");
		controller.addTypology(groundTypology1);
		
		FishGroup fishGroup1 = new FishGroup(15);
		controller.addClientFishGroup(client1, fishGroup1);
		
		TypologyGroup typologyGroup1 = new TypologyGroup(groundTypology1, 5);
		controller.addTypologyGroupToFishGroup(fishGroup1, typologyGroup1);
		TypologyGroup typologyGroup2 = new TypologyGroup(typology1, 2);
		controller.addTypologyGroupToFishGroup(fishGroup1, typologyGroup2);
		
		controller.setContainerToGroup(container1, fishGroup1);
	}
}
