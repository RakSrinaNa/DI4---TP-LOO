package fr.polytech.hibernate.tp11.view.post;

import fr.polytech.hibernate.tp11.Controller;
import javafx.scene.control.Tab;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class PostTab extends Tab
{
	public PostTab(Controller controller)
	{
		super("Posts");
		this.setContent(new PostTable(controller));
	}
}
