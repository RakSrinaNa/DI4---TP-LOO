package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Post implements Serializable
{
	private static final long serialVersionUID = -3182398014280731646L;
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	private int ID;
	
	private SimpleObjectProperty<User> author;
	
	private SimpleStringProperty content;
	
	private SimpleStringProperty title;
	
	private SimpleObjectProperty<LocalDateTime> date;
	
	@OneToOne(targetEntity = User.class)
	public User getAuthor()
	{
		return author.get();
	}
	
	public SimpleObjectProperty<User> authorProperty()
	{
		return author;
	}
	
	public void setAuthor(User author)
	{
		this.author.set(author);
	}
	
	@Column
	public String getContent()
	{
		return content.get();
	}
	
	public SimpleStringProperty contentProperty()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content.set(content);
	}
	
	@Column
	public String getTitle()
	{
		return title.get();
	}
	
	public SimpleStringProperty titleProperty()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title.set(title);
	}
	
	@Column
	public LocalDateTime getDate()
	{
		return date.get();
	}
	
	public SimpleObjectProperty<LocalDateTime> dateProperty()
	{
		return date;
	}
	
	public void setDate(LocalDateTime date)
	{
		this.date.set(date);
	}
}
