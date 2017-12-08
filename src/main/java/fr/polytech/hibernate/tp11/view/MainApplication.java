package fr.polytech.hibernate.tp11.view;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.view.model.UserTab;
import fr.polytech.hibernate.tp11.view.post.PostTab;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class MainApplication extends Application
{
	private Controller controller;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		controller = new Controller();
		controller.populateSome();
		
		primaryStage.setScene(createScene());
		primaryStage.setTitle("Hibernate");
		primaryStage.sizeToScene();
		primaryStage.setOnCloseRequest(evt -> controller.close());
		primaryStage.show();
	}
	
	private Scene createScene()
	{
		return new Scene(createContent());
	}
	
	private Parent createContent()
	{
		TabPane tabs = new TabPane();
		tabs.getTabs().addAll(new UserTab(controller));
		tabs.getTabs().addAll(new PostTab(controller));
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		return new StackPane(tabs);
	}
}
