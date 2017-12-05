package fr.polytech.hibernate.tp11.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setScene(createScene());
		primaryStage.setTitle("Hibernate");
		primaryStage.sizeToScene();
	}
	
	private Scene createScene()
	{
		return new Scene(createContent());
	}
	
	private Parent createContent()
	{
		return new StackPane();
	}
}
