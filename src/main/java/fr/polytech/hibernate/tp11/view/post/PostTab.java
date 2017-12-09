package fr.polytech.hibernate.tp11.view.post;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.model.Keyword;
import fr.polytech.hibernate.tp11.model.User;
import fr.polytech.hibernate.tp11.view.utils.RefreshableListCell;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class PostTab extends Tab
{
	public PostTab(Controller controller, User user)
	{
		super("Posts");
		HBox controls = new HBox();
		
		Button newPost = new Button("New post");
		newPost.setOnAction(evt -> controller.createPost(evt, user));
		
		DatePicker postDate = new DatePicker();
		postDate.setMaxWidth(Double.MAX_VALUE);
		
		ComboBox<Keyword> keywordFilter = new ComboBox<>();
		//Display departments as string instead of their hashcode
		Callback<ListView<Keyword>, ListCell<Keyword>> keywordCellFactory = param -> new RefreshableListCell<>(Keyword::keywordAsProperty);
		keywordFilter.setButtonCell(keywordCellFactory.call(null));
		keywordFilter.setCellFactory(keywordCellFactory);
		keywordFilter.setMaxWidth(Double.MAX_VALUE);
		keywordFilter.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.SPACE && evt.isControlDown())
			{
				((ComboBox) evt.getSource()).getSelectionModel().clearSelection();
				evt.consume();
			}
		});
		keywordFilter.setOnMouseClicked(evt -> {
			if(evt.getButton() == MouseButton.PRIMARY && evt.isControlDown())
			{
				((ComboBox) evt.getSource()).getSelectionModel().clearSelection();
				evt.consume();
			}
		});
		keywordFilter.setItems(controller.getKeywords());
		
		controls.getChildren().addAll(newPost, postDate, keywordFilter);
		
		VBox root = new VBox();
		PostTable postTable = new PostTable(controller, user, postDate.valueProperty(), keywordFilter.getSelectionModel().selectedItemProperty());
		root.getChildren().addAll(postTable, controls);
		
		HBox.setHgrow(newPost, Priority.SOMETIMES);
		HBox.setHgrow(postDate, Priority.SOMETIMES);
		HBox.setHgrow(keywordFilter, Priority.SOMETIMES);
		VBox.setVgrow(postTable, Priority.ALWAYS);
		this.setContent(root);
	}
}
