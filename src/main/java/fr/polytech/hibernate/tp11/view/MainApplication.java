package fr.polytech.hibernate.tp11.view;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.view.scenes.ApplicationScene;
import fr.polytech.hibernate.tp11.view.scenes.LoginScene;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
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
		
		LoginScene loginScene = new LoginScene(controller);
		loginScene.setOnLoggedIn(user -> {
			ApplicationScene applicationScene = new ApplicationScene(controller, user);
			primaryStage.setScene(applicationScene);
			primaryStage.setResizable(true);
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
			primaryStage.setMaximized(true);
		});
		primaryStage.setScene(loginScene);
		primaryStage.setTitle("Hibernate");
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(evt -> controller.close());
		primaryStage.show();
	}
}
