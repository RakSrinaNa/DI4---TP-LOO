package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Post implements Serializable
{
	private static final long serialVersionUID = -3182398014280731646L;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private int ID;
	private User author;
	private String content;
	private String title;
	private LocalDateTime date;
	private Set<Keyword> keywords;
	private Set<Link> links;
	private Set<Image> images;
	
	/**
	 * Constructor.
	 */
	public Post()
	{
		this.keywords = new LinkedHashSet<>();
		this.links = new LinkedHashSet<>();
		this.images = new LinkedHashSet<>();
	}
	
	/**
	 * Adds a keyword to the post.
	 * 
	 * @param keyword The keyword to add.
	 */
	public void addKeyword(Keyword keyword)
	{
		keywords.add(keyword);
		pcs.firePropertyChange("keywords", keywords, keywords);
	}
	
	/**
	 * Removes a keyword from the post.
	 * 
	 * @param keyword The keyword to remove.
	 */
	public void removeKeyword(Keyword keyword)
	{
		keywords.remove(keyword);
		pcs.firePropertyChange("keywords", keywords, keywords);
	}
	
	/**
	 * Adds a link to the post.
	 * 
	 * @param link The link to add.
	 */
	public void addLinks(Link link)
	{
		links.add(link);
		pcs.firePropertyChange("links", links, links);
	}
	
	/**
	 * Removes a link from the post.
	 * 
	 * @param link The link to remove.
	 */
	public void removeLinks(Link link)
	{
		links.remove(link);
		pcs.firePropertyChange("links", links, links);
	}
	
	/**
	 * Adds an image to the post.
	 * 
	 * @param image The image to add.
	 */
	public void addImages(Image image)
	{
		this.images.add(image);
		pcs.firePropertyChange("images", images, images);
	}
	
	/**
	 * Removes an image from the post.
	 * 
	 * @param image The image to remove.
	 */
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
	public Set<Image> getImages()
	{
		return images;
	}
	
	public void setImages(Set<Image> images)
	{
		Set<Image> old = this.images;
		this.images = images;
		pcs.firePropertyChange("images", old, images);
	}
	
	@ManyToMany(targetEntity = Keyword.class, fetch = FetchType.EAGER)
	public Set<Keyword> getKeywords()
	{
		return keywords;
	}
	
	public void setKeywords(Set<Keyword> keywords)
	{
		Set<Keyword> old = this.keywords;
		this.keywords = keywords;
		pcs.firePropertyChange("keywords", old, keywords);
	}
	
	@OneToMany(targetEntity = Link.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Link> getLinks()
	{
		return links;
	}
	
	public void setLinks(Set<Link> links)
	{
		Set<Link> old = this.links;
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
