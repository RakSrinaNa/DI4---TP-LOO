package fr.polytech.hibernate.tp11.view.user;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.model.User;
import javafx.scene.control.Tab;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class UserTab extends Tab
{
	public UserTab(Controller controller, User user)
	{
		super("Users");
		this.setContent(new UserTable(controller, user));
	}
}
