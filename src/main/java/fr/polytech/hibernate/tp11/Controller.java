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
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class Controller extends ControllerBase
{
	private final ObservableList<User> users;
	private final ObservableList<Post> posts;
	private final ObservableList<Keyword> keywords;
	
	public Controller() throws IllegalStateException
	{
		super();
		users = FXCollections.observableArrayList();
		users.addAll(getAllObject(User.class));
		posts = FXCollections.observableArrayList();
		posts.addAll(getAllObject(Post.class));
		keywords = FXCollections.observableArrayList();
		keywords.addAll(getAllObject(Keyword.class));
	}
	
	public void populateSome()
	{
		Address a1 = new Address("16 Rue du pif", "City", "Poitou", "1", "USA");
		User u1 = new User("a", "Jean", "Bon", a1, "jb@prout.fr", "pass");
		addUser(u1);
		
		Address a2 = new Address("17 Rue du pif", "City", "Poitou", "1", "USA");
		User u2 = new User("b", "Jane", "Bon", a2, "jb2@prout.fr", "pass");
		addUser(u2);
	}
	
	private void addUser(User user)
	{
		persistObject(user);
		users.add(user);
	}
	
	private void addPost(Post post)
	{
		persistObject(post);
		posts.add(post);
	}
	
	public void onUserChanged(User user)
	{
		updateObject(user);
	}
	
	public void onPostChanged(Post post)
	{
		updateObject(post);
	}
	
	public User loginUser(String username, String password)
	{
		ArrayList<Pair<String, Object>> constraints = new ArrayList<>();
		constraints.add(new Pair<>("username", username));
		constraints.add(new Pair<>("password", password));
		List<User> users = findEqual(User.class, constraints);
		return users.size() > 0 ? users.get(0) : null;
	}
	
	public void deletePost(Post post)
	{
		//post.getAuthor().removePost(post);
		posts.remove(post);
		deleteObject(post);
		//TODO: Need to detach?
	}
	
	public void createPost(ActionEvent event, User user)
	{
		PostCreateDialog dialog = new PostCreateDialog(this);
		dialog.initOwner(((Button) event.getSource()).getScene().getWindow());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
		Post result = dialog.getResult();
		if(result != null) //If an employee was created
		{
		result.setAuthor(user);
			user.addPost(result);
			updateObject(user);
			posts.add(result);
		}
	}
	
	public void modifyPost(Event event, Post post)
	{
		PostCreateDialog dialog = new PostCreateDialog(this, post);
		dialog.initOwner(((Node)event.getSource()).getScene().getWindow());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
		Post result = dialog.getResult();
		if(result != null) //If an employee was created
		{
			updateObject(post);
		}
	}
	
	public void infosPost(Node parent, Post post)
	{
		PostInfosDialog dialog = new PostInfosDialog(post);
		dialog.initOwner(parent.getScene().getWindow());
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}
	
	public void addKeyword(Keyword keyword)
	{
		persistObject(keyword);
		keywords.add(keyword);
	}
	
	public ObservableList<Keyword> getKeywords()
	{
		return keywords;
	}
	
	public ObservableList<Post> getPosts()
	{
		return posts;
	}
	
	public ObservableList<User> getUsers()
	{
		return users;
	}
	
	@Override
	protected String getPackage()
	{
		return "fr.polytech.hibernate.tp11.model";
	}
}
