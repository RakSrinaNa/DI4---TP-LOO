package fr.polytech.hibernate.tp11.view.scenes;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.function.Consumer;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 08/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-08
 */
public class LoginScene extends Scene
{
	private final Controller controller;
	private Consumer<User> onLoggedIn;
	
	public LoginScene(Controller controller)
	{
		super(new StackPane());
		this.controller = controller;
		this.setRoot(buildContent());
	}
	
	private Parent buildContent()
	{
		VBox root = new VBox();
		
		HBox userBox = new HBox();
		TextField userArea = new TextField();
		userBox.getChildren().addAll(new Text("Username: "), userArea);
		userBox.setMaxWidth(Double.MAX_VALUE);
		
		HBox passwordBox = new HBox();
		PasswordField passwordArea = new PasswordField();
		passwordBox.getChildren().addAll(new Text("Password: "), passwordArea);
		passwordBox.setMaxWidth(Double.MAX_VALUE);
		
		Button valid = new Button("Login");
		
		Runnable login = () -> {
			User user;
			if((user = controller.loginUser(userArea.getText(), passwordArea.getText())) != null)
			{
				if(LoginScene.this.onLoggedIn != null)
					LoginScene.this.onLoggedIn.accept(user);
			}
			else
				passwordArea.setText("");
		};
		
		valid.setOnAction(evt -> login.run());
		valid.setMaxWidth(Double.MAX_VALUE);
		
		userBox.setOnKeyPressed(evt -> {
			if(evt.getCode().equals(KeyCode.ENTER))
				login.run();
		});
		passwordBox.setOnKeyPressed(evt -> {
			if(evt.getCode().equals(KeyCode.ENTER))
				login.run();
		});
		
		HBox.setHgrow(userBox, Priority.ALWAYS);
		HBox.setHgrow(passwordBox, Priority.ALWAYS);
		root.getChildren().addAll(userBox, passwordBox, valid);
		return root;
	}
	
	public void setOnLoggedIn(Consumer<User> onLoggedIn)
	{
		this.onLoggedIn = onLoggedIn;
	}
}
