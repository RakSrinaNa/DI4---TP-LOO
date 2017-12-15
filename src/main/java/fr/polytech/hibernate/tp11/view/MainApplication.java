package fr.polytech.hibernate.tp11.view;

import fr.polytech.hibernate.tp11.BlogController;
import fr.polytech.hibernate.tp11.view.scenes.ApplicationScene;
import fr.polytech.hibernate.tp11.view.scenes.LoginScene;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class MainApplication extends Application
{
	private BlogController blogController;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		blogController = new BlogController();
		blogController.populateSome();
		
		LoginScene loginScene = new LoginScene(blogController);
		loginScene.setOnLoggedIn(user -> {
			ApplicationScene applicationScene = new ApplicationScene(blogController, user);
			primaryStage.setScene(applicationScene);
			primaryStage.setResizable(true);
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			primaryStage.setMinHeight(200);
			primaryStage.setMinWidth(200);
			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
			primaryStage.setMaximized(true);
		});
		primaryStage.getIcons().add(getIcon());
		primaryStage.setScene(loginScene);
		primaryStage.setTitle("Blogos");
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(evt -> blogController.close());
		primaryStage.show();
	}
	
	private Image getIcon()
	{
		try
		{
			return SwingFXUtils.toFXImage(resizeBufferedImage(ImageIO.read(getResource("icon.png")), 256, 256), null);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private URL getResource(String path)
	{
		return MainApplication.class.getResource("/jfx/" + path);
	}
	
	private static BufferedImage resizeBufferedImage(BufferedImage image, float width, float height)
	{
		int baseWidth = image.getWidth(), baseHeight = image.getHeight();
		float ratio = baseWidth > baseHeight ? width / baseWidth : height / baseHeight;
		java.awt.Image tmp = image.getScaledInstance((int) (ratio * baseWidth), (int) (ratio * baseHeight), BufferedImage.SCALE_SMOOTH);
		BufferedImage buffered = new BufferedImage((int) (ratio * baseWidth), (int) (ratio * baseHeight), BufferedImage.TYPE_INT_ARGB);
		buffered.getGraphics().drawImage(tmp, 0, 0, null);
		return buffered;
	}
}
