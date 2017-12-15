package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.beans.PropertyChangeSupport;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
@Entity
@Controlled
@Access(value = AccessType.PROPERTY)
public class User implements Externalizable
{
	private static final long serialVersionUID = -5713444165550083714L;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final Set<Post> posts;
	private int ID;
	private String firstname;
	private String username;
	private String lastname;
	private Address address;
	private String mail;
	private String password;
	
	public User(String username, String firstname, String lastname, Address address, String mail, String password)
	{
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.mail = mail;
		this.password = password;
		this.posts = new LinkedHashSet<>();
	}
	
	public User()
	{
		this.posts = new LinkedHashSet<>();
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		out.writeObject(getAddress());
		out.writeInt(getID());
		out.writeUTF(getFirstname());
		out.writeUTF(getLastname());
		out.writeUTF(getMail());
		out.writeUTF(getPassword());
		out.writeObject(getPosts());
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		setAddress((Address) in.readObject());
		setID(in.readInt());
		setFirstname(in.readUTF());
		setLastname(in.readUTF());
		setMail(in.readUTF());
		setPassword(in.readUTF());
		setPosts((Set<Post>) in.readObject());
	}
	
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	public Address getAddress()
	{
		return address;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		int old = this.ID;
		this.ID = ID;
		pcs.firePropertyChange("ID", old, ID);
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public String getMail()
	{
		return mail;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Post.class)
	public Set<Post> getPosts()
	{
		return posts;
	}
	
	public void setPosts(Set<Post> posts)
	{
		this.posts.clear();
		this.posts.addAll(posts);
		pcs.firePropertyChange("posts", posts, posts);
	}
	
	public void setPassword(String password)
	{
		String old = this.password;
		this.password = password;
		pcs.firePropertyChange("password", old, password);
	}
	
	public void setMail(String mail)
	{
		String old = this.mail;
		this.mail = mail;
		pcs.firePropertyChange("mail", old, mail);
	}
	
	public void setLastname(String lastname)
	{
		String old = this.lastname;
		this.lastname = lastname;
		pcs.firePropertyChange("lastname", old, lastname);
	}
	
	public void setFirstname(String firstname)
	{
		String old = this.firstname;
		this.firstname = firstname;
		pcs.firePropertyChange("firstname", old, firstname);
	}
	
	public void setAddress(Address address)
	{
		Address old = this.address;
		this.address = address;
		pcs.firePropertyChange("address", old, address);
	}
	
	public void removePost(Post post)
	{
		posts.remove(post);
		pcs.firePropertyChange("address", posts, posts);
	}
	
	public void addPost(Post result)
	{
		posts.add(result);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof User && getID() == ((User) obj).getID();
	}
	
	@Override
	public String toString()
	{
		return getFirstname() + " " + getLastname();
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		String old = this.username;
		this.username = username;
		pcs.firePropertyChange("username", old, username);
	}
}
