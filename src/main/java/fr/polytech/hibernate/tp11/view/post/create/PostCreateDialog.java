package fr.polytech.hibernate.tp11.view.post.create;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.model.Image;
import fr.polytech.hibernate.tp11.model.Keyword;
import fr.polytech.hibernate.tp11.model.Link;
import fr.polytech.hibernate.tp11.model.Post;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A dialog to create an employee.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 27/04/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-04-27
 */
public class PostCreateDialog extends Stage
{
	private final Controller parentController;
	private final PostCreateDialogController controller;
	private Post result;
	private TextArea titleText;
	private TextArea contentText;
	private TextArea keywordsText;
	private TextArea linksText;
	private TextArea imagesText;
	
	public PostCreateDialog(Controller controller)
	{
		this(controller, null);
	}
	
	/**
	 * Constructor.
	 */
	public PostCreateDialog(Controller parentController, Post initialPost)
	{
		super();
		this.parentController = parentController;
		controller = new PostCreateDialogController(this);
		if(initialPost == null)
			initialPost = new Post();
		setOnCloseRequest(evt -> setResult(null));
		setResult(initialPost);
		setTitle("Create post");
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
		validButton.setOnAction(controller::valid);
		validButton.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.TAB)
			{
				titleText.requestFocus();
				evt.consume();
			}
		});
		
		Label titleLabel = new Label("Title: ");
		titleText = new TextArea();
		titleText.setMaxWidth(Double.MAX_VALUE);
		titleText.setPrefRowCount(1);
		titleText.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.TAB)
			{
				contentText.requestFocus();
				evt.consume();
			}
		});
		HBox titleBox = new HBox();
		titleBox.getChildren().addAll(titleLabel, titleText);
		HBox.setHgrow(titleLabel, Priority.SOMETIMES);
		HBox.setHgrow(titleText, Priority.ALWAYS);
		
		Label contentLabel = new Label("Content: ");
		contentText = new TextArea();
		contentText.setPrefRowCount(10);
		contentText.setMaxWidth(Double.MAX_VALUE);
		contentText.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.TAB)
			{
				keywordsText.requestFocus();
				evt.consume();
			}
		});
		HBox contentBox = new HBox();
		contentBox.getChildren().addAll(contentLabel, contentText);
		HBox.setHgrow(contentLabel, Priority.SOMETIMES);
		HBox.setHgrow(contentText, Priority.ALWAYS);
		
		Label keywordsLabel = new Label("Keywords: ");
		keywordsText = new TextArea();
		keywordsText.setPrefRowCount(1);
		keywordsText.setMaxWidth(Double.MAX_VALUE);
		keywordsText.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.TAB)
			{
				linksText.requestFocus();
				evt.consume();
			}
		});
		HBox keywordsBox = new HBox();
		keywordsBox.getChildren().addAll(keywordsLabel, keywordsText);
		HBox.setHgrow(keywordsLabel, Priority.SOMETIMES);
		HBox.setHgrow(keywordsText, Priority.ALWAYS);
		
		Label linksLabel = new Label("Links: ");
		linksText = new TextArea();
		linksText.setPrefRowCount(1);
		linksText.setMaxWidth(Double.MAX_VALUE);
		linksText.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.TAB)
			{
				imagesText.requestFocus();
				evt.consume();
			}
		});
		HBox linksBox = new HBox();
		linksBox.getChildren().addAll(linksLabel, linksText);
		HBox.setHgrow(linksLabel, Priority.SOMETIMES);
		HBox.setHgrow(linksText, Priority.ALWAYS);
		
		Label imagesLabel = new Label("Images: ");
		imagesText = new TextArea();
		imagesText.setPrefRowCount(1);
		imagesText.setMaxWidth(Double.MAX_VALUE);
		imagesText.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.TAB)
			{
				validButton.requestFocus();
				evt.consume();
			}
		});
		HBox imagesBox = new HBox();
		imagesBox.getChildren().addAll(imagesLabel, imagesText);
		HBox.setHgrow(imagesLabel, Priority.SOMETIMES);
		HBox.setHgrow(imagesText, Priority.ALWAYS);
		
		root.getChildren().addAll(titleBox, contentBox, keywordsBox, linksBox, imagesBox, validButton);
		VBox.setVgrow(titleBox, Priority.NEVER);
		VBox.setVgrow(contentBox, Priority.NEVER);
		VBox.setVgrow(keywordsBox, Priority.SOMETIMES);
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
	
	public List<Image> getImages()
	{
		return imagesText.getText().equals("") ? new ArrayList<>() : Arrays.stream(imagesText.getText().split(" ")).map(Image::new).collect(Collectors.toList());
	}
	
	/**
	 * Get the entered last name.
	 *
	 * @return The last name.
	 */
	public String getContent()
	{
		return contentText.getText().trim();
	}
	
	public List<Keyword> getKeywords()
	{
		ArrayList<Keyword> kw = new ArrayList<>();
		
		for(String k : keywordsText.getText().split(" "))
			kw.add(parentController.getKeywords().stream().filter(kk -> kk.is(k)).findFirst().orElseGet(() -> {
				Keyword kkw = new Keyword(k);
				parentController.addKeyword(kkw);
				return kkw;
			}));
		
		return kw;
	}
	
	public List<Link> getLinks()
	{
		return convertStringToURL(pullLinks(linksText.getText())).stream().map(Link::new).collect(Collectors.toList());
	}
	
	private static List<URL> convertStringToURL(List<String> strings)
	{
		LinkedList<URL> urls = new LinkedList<>();
		for(String urlString : strings)
			try
			{
				urls.add(new URL(urlString));
			}
			catch(MalformedURLException e)
			{
				e.printStackTrace();
			}
		return urls;
	}
	
	private static List<String> pullLinks(String text)
	{
		LinkedList<String> links = new LinkedList<>();
		Matcher matcher = Pattern.compile("\\(?\\b(http://|https://|www[.])[-A-Za-z0-9+&@#/\\\\%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]").matcher(text);
		while(matcher.find())
		{
			String urlString = matcher.group();
			if(urlString.startsWith("(") && urlString.endsWith(")"))
				urlString = urlString.substring(1, urlString.length() - 1);
			//noinspection deprecation
			links.add(urlString);
		}
		HashSet<String> hs = new HashSet<>(links);
		links.clear();
		links.addAll(hs);
		return links;
	}
	
	/**
	 * Get the built employee.
	 *
	 * @return The employee.
	 */
	public Post getResult()
	{
		return result;
	}
	
	/**
	 * Set the built employee.
	 *
	 * @param employee The built employee.
	 */
	private void setResult(Post employee)
	{
		result = employee;
	}
	
	/**
	 * Get the entered first name.
	 *
	 * @return The first name.
	 */
	public String getTitlee()
	{
		return titleText.getText().trim();
	}
}
