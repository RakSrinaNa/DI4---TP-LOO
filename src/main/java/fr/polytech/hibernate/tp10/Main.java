package fr.polytech.hibernate.tp10;

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
		Controller c = new Controller();
		MainWindow mw = new MainWindow(c);
		mw.setVisible(true);
	}
}
