package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-09
 */
@Entity
@Controlled
@Access(value = AccessType.PROPERTY)
public class Keyword implements Serializable
{
	private int ID;
	private String keyword;
	
	/**
	 * Constructor
	 * 
	 * @param key The keyword itself.
	 */
	public Keyword(String key)
	{
		this.keyword = key;
	}
	
	/**
	 * Constructor.
	 */
	public Keyword(){}
	
	/**
	 * Gives a property from the keyword, for use with JavaFX.
	 * 
	 * @return The property.
	 */
	public SimpleStringProperty keywordAsProperty()
	{
		return new SimpleStringProperty(getKeyword());
	}
	
	public String getKeyword()
	{
		return keyword;
	}
	
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}
	
	/**
	 * Checks if an object is equal to the keyword.
	 * 
	 * @param obj The object to compare.
	 * @return true if the object equals the keyword, false otherwise.
	 */
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof Keyword && ((Keyword)obj).is(keyword);
	}
	
	/**
	 * Checks if a keyword is the same.
	 * 
	 * @param key The keyword to compare.
	 * @return true if the keywords are equal, false otherwise.
	 */
	public boolean is(String key)
	{
		return keyword.equals(key);
	}
	
	/**
	 * @return a string version of the keyword.
	 */
	@Override
	public String toString()
	{
		return getKeyword();
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
}
