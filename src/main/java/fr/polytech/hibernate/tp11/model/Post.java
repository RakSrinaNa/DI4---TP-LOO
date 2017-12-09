package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	private int ID;
	private User author;
	private String content;
	private String title;
	private LocalDateTime date;
	private List<Keyword> keywords;
	private List<Link> links;
	private List<Image> images;
	
	public Post()
	{
		this.keywords = new ArrayList<>();
		this.links = new ArrayList<>();
		this.images = new ArrayList<>();
	}
	
	public void addKeyword(Keyword keyword)
	{
		keywords.add(keyword);
		pcs.firePropertyChange("keywords", keywords, keywords);
	}
	
	public void removeKeyword(Keyword keyword)
	{
		keywords.remove(keyword);
		pcs.firePropertyChange("keywords", keywords, keywords);
	}
	
	public void addLinks(Link link)
	{
		links.add(link);
		pcs.firePropertyChange("links", links, links);
	}
	
	public void removeLinks(Link link)
	{
		links.remove(link);
		pcs.firePropertyChange("links", links, links);
	}
	
	public void addImages(Image image)
	{
		this.images.add(image);
		pcs.firePropertyChange("images", images, images);
	}
	
	public void removeImages(Image image)
	{
		this.images.remove(image);
		pcs.firePropertyChange("images", images, images);
	}
	
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
	
	@OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Image> getImages()
	{
		return images;
	}
	
	public void setImages(List<Image> images)
	{
		List<Image> old = this.images;
		this.images = images;
		pcs.firePropertyChange("images", old, images);
	}
	
	@ManyToMany(targetEntity = Keyword.class, fetch = FetchType.EAGER)
	public List<Keyword> getKeywords()
	{
		return keywords;
	}
	
	public void setKeywords(List<Keyword> keywords)
	{
		List<Keyword> old = this.keywords;
		this.keywords = keywords;
		pcs.firePropertyChange("keywords", old, keywords);
	}
	
	@OneToMany(targetEntity = Link.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Link> getLinks()
	{
		return links;
	}
	
	public void setLinks(List<Link> links)
	{
		List<Link> old = this.links;
		this.links = links;
		pcs.firePropertyChange("links", old, links);
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
}
