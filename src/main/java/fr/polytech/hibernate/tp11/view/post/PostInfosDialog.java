package fr.polytech.hibernate.tp11.view.post;

import fr.polytech.hibernate.tp11.model.Image;
import fr.polytech.hibernate.tp11.model.Keyword;
import fr.polytech.hibernate.tp11.model.Link;
import fr.polytech.hibernate.tp11.model.Post;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * A dialog to create an employee.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 27/04/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-04-27
 */
public class PostInfosDialog extends Stage
{
	/**
	 * Constructor.
	 */
	public PostInfosDialog(Post initialPost)
	{
		super();
		setTitle("Post infos");
		setScene(new Scene(buildStage(initialPost)));
		sizeToScene();
	}
	
	/**
	 * Build the content.
	 *
	 * @return The root element.
	 */
	private Parent buildStage(Post initialPost)
	{
		VBox root = new VBox(3);
		
		Button validButton = new Button("OK");
		validButton.setMaxWidth(Double.MAX_VALUE);
		validButton.setMaxHeight(Double.MAX_VALUE);
		validButton.setOnAction(evt -> this.close());
		
		Label titleLabel = new Label("Title: ");
		Label titleText = new Label();
		titleText.setMaxWidth(Double.MAX_VALUE);
		HBox titleBox = new HBox();
		titleBox.getChildren().addAll(titleLabel, titleText);
		HBox.setHgrow(titleLabel, Priority.SOMETIMES);
		HBox.setHgrow(titleText, Priority.ALWAYS);
		
		Label contentLabel = new Label("Content: ");
		Label contentText = new Label();
		contentText.setMaxWidth(Double.MAX_VALUE);
		HBox contentBox = new HBox();
		contentBox.getChildren().addAll(contentLabel, contentText);
		HBox.setHgrow(contentLabel, Priority.SOMETIMES);
		HBox.setHgrow(contentText, Priority.ALWAYS);
		
		Label keywordsLabel = new Label("Keywords: ");
		Label keywordsText = new Label();
		keywordsText.setMaxWidth(Double.MAX_VALUE);
		HBox keywordsBox = new HBox();
		keywordsBox.getChildren().addAll(keywordsLabel, keywordsText);
		HBox.setHgrow(keywordsLabel, Priority.SOMETIMES);
		HBox.setHgrow(keywordsText, Priority.ALWAYS);
		
		Label linksLabel = new Label("Links: ");
		Label linksText = new Label();
		linksText.setMaxWidth(Double.MAX_VALUE);
		HBox linksBox = new HBox();
		linksBox.getChildren().addAll(linksLabel, linksText);
		HBox.setHgrow(linksLabel, Priority.SOMETIMES);
		HBox.setHgrow(linksText, Priority.ALWAYS);
		
		Label imagesLabel = new Label("Images: ");
		Label imagesText = new Label();
		imagesText.setMaxWidth(Double.MAX_VALUE);
		HBox imagesBox = new HBox();
		imagesBox.getChildren().addAll(imagesLabel, imagesText);
		HBox.setHgrow(imagesLabel, Priority.SOMETIMES);
		HBox.setHgrow(imagesText, Priority.ALWAYS);
		
		root.getChildren().addAll(titleBox, contentBox, keywordsBox, linksBox, imagesBox, validButton);
		VBox.setVgrow(titleBox, Priority.NEVER);
		VBox.setVgrow(contentBox, Priority.NEVER);
		VBox.setVgrow(keywordsBox, Priority.SOMETIMES);
		VBox.setVgrow(imagesBox, Priority.SOMETIMES);
		VBox.setVgrow(linksBox, Priority.SOMETIMES);
		VBox.setVgrow(validButton, Priority.ALWAYS);
		
		if(initialPost.getTitle() != null)
			titleText.setText(initialPost.getTitle());
		if(initialPost.getContent() != null)
			contentText.setText(initialPost.getContent());
		if(initialPost.getKeywords() != null)
			keywordsText.setText(initialPost.getKeywords().stream().map(Keyword::getKeyword).collect(Collectors.joining(" ")));
		if(initialPost.getImages() != null)
			imagesText.setText(initialPost.getImages().stream().map(Image::getPath).collect(Collectors.joining(" ")));
		if(initialPost.getLinks() != null)
			linksText.setText(initialPost.getLinks().stream().map(Link::getUrl).map(URL::toString).collect(Collectors.joining(" ")));
		
		return root;
	}
}
