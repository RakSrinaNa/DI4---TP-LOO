package fr.polytech.hibernate.tp11.view.post.create;

import fr.polytech.hibernate.tp11.model.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.time.LocalDateTime;

/**
 * BlogController for the employee creation dialog.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 27/04/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-04-27
 */
public class PostCreateDialogController
{
	private final PostCreateDialog view;
	
	/**
	 * Constructor.
	 *
	 * @param view The view.
	 */
	public PostCreateDialogController(PostCreateDialog view)
	{
		this.view = view;
	}
	
	/**
	 * Called when the employee need to be created.
	 *
	 * @param actionEvent The click event.
	 */
	public void valid(ActionEvent actionEvent)
	{
		if(!view.getTitlee().equals("")) //If all the mandatory elements are given
		{
			Post post = view.getResult();
			post.setTitle(view.getTitlee());
			post.setContent(view.getContent());
			post.setKeywords(view.getKeywords());
			post.setLinks(view.getLinks());
			post.setImages(view.getImages());
			post.setDate(LocalDateTime.now());
			view.close();
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Post cannot be created");
			alert.setHeaderText("Post cannot be created");
			alert.setContentText("You need to give a title");
			alert.showAndWait();
		}
	}
}
