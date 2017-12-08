package fr.polytech.hibernate.tp11;

import fr.polytech.hibernate.base.ControllerBase;
import fr.polytech.hibernate.tp11.model.Address;
import fr.polytech.hibernate.tp11.model.Post;
import fr.polytech.hibernate.tp11.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	
	public Controller() throws IllegalStateException
	{
		super();
		users = FXCollections.observableArrayList();
		users.addAll(getAllObject(User.class));
		posts = FXCollections.observableArrayList();
		posts.addAll(getAllObject(Post.class));
	}
	
	public void populateSome()
	{
		Address a1 = new Address("16 Rue du pif", "City", "Poitou", "1", "USA");
		User u1 = new User("Jean", "Bon", a1, "jb@prout.fr", "pass");
		addUser(u1);
		
		
		Address a2 = new Address("17 Rue du pif", "City", "Poitou", "1", "USA");
		User u2 = new User("Jane", "Bon", a2, "jb2@prout.fr", "pass");
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
