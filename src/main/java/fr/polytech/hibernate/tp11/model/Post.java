package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
@Entity
@Controlled
@Access(value = AccessType.PROPERTY)
public class Post implements Serializable
{
	private static final long serialVersionUID = -3182398014280731646L;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
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
	
	private User author;
	
	private String content;
	
	private String title;
	
	private LocalDateTime date;
	
	@OneToOne(targetEntity = User.class)
	public User getAuthor()
	{
		return author;
	}
	
	public void setAuthor(User author)
	{
		User old = this.author;
		this.author = author;
		pcs.firePropertyChange("author", old, author);
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		String old = this.content;
		this.content = content;
		pcs.firePropertyChange("content", old, content);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		String old = this.title;
		this.title = title;
		pcs.firePropertyChange("title", old, title);
	}
	
	public LocalDateTime getDate()
	{
		return date;
	}
	
	public void setDate(LocalDateTime date)
	{
		LocalDateTime old = this.date;
		this.date = date;
		pcs.firePropertyChange("date", old, date);
	}
}
