package fr.polytech.hibernate.tp11.view.scenes;

import fr.polytech.hibernate.tp11.BlogController;
import fr.polytech.hibernate.tp11.model.User;
import fr.polytech.hibernate.tp11.view.user.UserTab;
import fr.polytech.hibernate.tp11.view.post.PostTab;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 08/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-08
 */
public class ApplicationScene extends Scene
{
	private final BlogController blogController;
	private final User user;
	
	public ApplicationScene(BlogController blogController, User user)
	{
		super(new StackPane());
		this.blogController = blogController;
		this.user = user;
		this.setRoot(buildContent());
	}
	
	private Parent buildContent()
	{
		TabPane tabs = new TabPane();
		tabs.getTabs().addAll(new UserTab(blogController, user));
		tabs.getTabs().addAll(new PostTab(blogController, user));
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		return new StackPane(tabs);
	}
}
