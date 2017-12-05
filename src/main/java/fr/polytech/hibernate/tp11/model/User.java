package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
@Entity
@Table
@Controlled
public class User implements Serializable
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Transient
	private SimpleStringProperty firstname;
	
	@Transient
	private SimpleStringProperty lastname;
	
	@Transient
	private SimpleObjectProperty<Address> address;
	
	@Transient
	private SimpleStringProperty mail;
	
	@Transient
	private SimpleStringProperty password;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Post.class)
	private List<Post> posts;
	
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
	
	@Column
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
}
