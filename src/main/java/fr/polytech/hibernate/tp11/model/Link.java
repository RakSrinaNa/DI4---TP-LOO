package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.net.URL;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-09
 */
@Entity
@Controlled
@Access(value = AccessType.PROPERTY)
public class Link
{
	private int ID;
	private String displayName;
	private URL url;
	
	public Link(URL url)
	{
		this.url = url;
		this.displayName = url.toString();
	}
	
	public Link(){}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
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
	
	public URL getUrl()
	{
		return url;
	}
	
	public void setUrl(URL url)
	{
		this.url = url;
	}
	
	@Override
	public String toString()
	{
		return "{" + "displayName='" + displayName + '\'' + ", url=" + url + '}';
	}
}
