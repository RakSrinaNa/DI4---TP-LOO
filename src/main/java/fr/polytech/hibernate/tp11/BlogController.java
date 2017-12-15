package fr.polytech.hibernate.tp11;

import fr.polytech.hibernate.base.ControllerBase;
import fr.polytech.hibernate.tp11.model.Address;
import fr.polytech.hibernate.tp11.model.Keyword;
import fr.polytech.hibernate.tp11.model.Post;
import fr.polytech.hibernate.tp11.model.User;
import fr.polytech.hibernate.tp11.view.post.PostInfosDialog;
import fr.polytech.hibernate.tp11.view.post.create.PostCreateDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * BlogController for the blog.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class BlogController extends ControllerBase
{
	private final ObservableList<User> users;
	private final ObservableList<Post> posts;
	private final ObservableList<Keyword> keywords;
	
	/**
	 * Constructor.
	 *
	 * @throws IllegalStateException
	 */
	public BlogController() throws IllegalStateException
	{
		super();
		users = FXCollections.observableArrayList();
		users.addAll(getElements(User.class));
		posts = FXCollections.observableArrayList();
		posts.addAll(getElements(Post.class));
		keywords = FXCollections.observableArrayList();
		keywords.addAll(getElements(Keyword.class));
	}
	
	/**
	 * Add some sample data into the database.
	 */
	public void populateSome()
	{
		Address a1 = new Address("16 Rue Jean Test", "Ville1", "Poitou-Charentes", "12345", "France");
		User u1 = new User("a", "Jean", "Utilisateur", a1, "jean@test.fr", "pass");
		addUser(u1);
		
		Address a2 = new Address("14 Rue du test", "Ville2", "Limousin", "66666", "France");
		User u2 = new User("b", "Pierre", "Test", a2, "pierre@exemple.fr", "pass");
		addUser(u2);
	}
	
	/**
	 * Add a user.
	 *
	 * @param user The user to add.
	 */
	private void addUser(User user)
	{
		persistObject(user);
		users.add(user);
	}
	
	/**
	 * Add a post.
	 *
	 * @param post The post to add.
	 */
	private void addPost(Post post)
	{
		persistObject(post);
		posts.add(post);
	}
	
	/**
	 * Modify a user.
	 *
	 * @param user The user modified.
	 */
	public void onUserChanged(User user)
	{
		updateObject(user);
	}
	
	/**
	 * Modify a post.
	 *
	 * @param post The post modified.
	 */
	public void onPostChanged(Post post)
	{
		updateObject(post);
	}
	
	/**
	 * Get a user with its username and password.
	 *
	 * @param username The username.
	 * @param password The password.
	 *
	 * @return The logged user, or null.
	 */
	public User loginUser(String username, String password)
	{
		ArrayList<Pair<String, Object>> constraints = new ArrayList<>();
		constraints.add(new Pair<>("username", username));
		constraints.add(new Pair<>("password", password));
		List<User> users = findEqual(User.class, constraints);
		return users.size() > 0 ? users.get(0) : null;
	}
	
	/**
	 * Delete a post.
	 *
	 * @param post The post to delete.
	 */
	public void deletePost(Post post)
	{
		posts.remove(post);
		deleteObject(post);
	}
	
	/**
	 * Create a new post.
	 *
	 * @param event The event that lead to it.
	 * @param user  The user creating the post.
	 */
	public void createPost(ActionEvent event, User user)
	{
		PostCreateDialog dialog = new PostCreateDialog(this);
		dialog.initOwner(((Button) event.getSource()).getScene().getWindow());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
		Post result = dialog.getResult();
		if(result != null) //If a post was created
		{
			result.setAuthor(user);
			user.addPost(result);
			updateObject(user);
			posts.add(result);
		}
	}
	
	/**
	 * Modify a post.
	 *
	 * @param event The event that lead to it.
	 * @param post  The post to modify.
	 */
	public void modifyPost(Event event, Post post)
	{
		PostCreateDialog dialog = new PostCreateDialog(this, post);
		dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
		Post result = dialog.getResult();
		if(result != null) //if a post was modified
			updateObject(post);
	}
	
	/**
	 * Displays the information on a post.
	 *
	 * @param parent The parent of the event.
	 * @param post   The post to display.
	 */
	public void infosPost(Node parent, Post post)
	{
		PostInfosDialog dialog = new PostInfosDialog(post);
		dialog.initOwner(parent.getScene().getWindow());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}
	
	/**
	 * Add a keyword to the database.
	 *
	 * @param keyword The keyword to add.
	 */
	public void addKeyword(Keyword keyword)
	{
		persistObject(keyword);
		keywords.add(keyword);
	}
	
	/**
	 * Get all the keywords.
	 *
	 * @return The keywords.
	 */
	public ObservableList<Keyword> getKeywords()
	{
		return keywords;
	}
	
	@Override
	protected String getPackage()
	{
		return "fr.polytech.hibernate.tp11.model";
	}
	
	/**
	 * Get all the posts.
	 *
	 * @return The posts.
	 */
	public ObservableList<Post> getPosts()
	{
		return posts;
	}
	
	/**
	 * Get all the users.
	 *
	 * @return The users.
	 */
	public ObservableList<User> getUsers()
	{
		return users;
	}
}
