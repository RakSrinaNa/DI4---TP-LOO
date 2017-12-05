package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
@Entity
@Table
@Controlled
@Access(value = AccessType.PROPERTY)
public class User implements Serializable
{
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getID()
	{
		return ID;
	}
	
	private static final long serialVersionUID = -5713444165550083714L;
	
	private int ID;
	
	private SimpleStringProperty firstname;
	
	private SimpleStringProperty lastname;
	
	private SimpleObjectProperty<Address> address;
	
	private SimpleStringProperty mail;
	
	private SimpleStringProperty password;
	
	private List<Post> posts;
	
	public User(String firstname, String lastname, Address address, String mail, String password)
	{
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.address = new SimpleObjectProperty<>(address);
		this.mail = new SimpleStringProperty(mail);
		this.password = new SimpleStringProperty(password);
	}
	
	public User(){}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Post.class)
	public List<Post> getPosts()
	{
		return posts;
	}
	
	public void setPosts(List<Post> posts)
	{
		this.posts = posts;
	}
	
	@Column
	public String getFirstname()
	{
		return firstname.get();
	}
	
	public SimpleStringProperty firstnameProperty()
	{
		return firstname;
	}
	
	public void setFirstname(String firstname)
	{
		this.firstname.set(firstname);
	}
	
	@Column
	public String getLastname()
	{
		return lastname.get();
	}
	
	public SimpleStringProperty lastnameProperty()
	{
		return lastname;
	}
	
	public void setLastname(String lastname)
	{
		this.lastname.set(lastname);
	}
	
	@OneToOne(targetEntity = Address.class)
	public Address getAddress()
	{
		return address.get();
	}
	
	public SimpleObjectProperty<Address> addressProperty()
	{
		return address;
	}
	
	public void setAddress(Address address)
	{
		this.address.set(address);
	}
	
	@Column
	public String getMail()
	{
		return mail.get();
	}
	
	public SimpleStringProperty mailProperty()
	{
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail.set(mail);
	}
	
	@Column
	public String getPassword()
	{
		return password.get();
	}
	
	public SimpleStringProperty passwordProperty()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password.set(password);
	}
	
	@Override
	public int hashCode()
	{
		return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof User && getID() == ((User)obj).getID();
	}
}
