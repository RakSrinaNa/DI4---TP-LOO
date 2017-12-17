package fr.polytech.jdbc.td4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-09
 */
public class Book
{
	private final int ISBN;
	private SimpleStringProperty description;
	private SimpleStringProperty sujet;
	private SimpleIntegerProperty nCopies;
	
	public Book(int ISBN, String description, String sujet, int nCopies)
	{
		this.ISBN = ISBN;
		this.description = new SimpleStringProperty(description);
		this.sujet = new SimpleStringProperty(sujet);
		this.nCopies = new SimpleIntegerProperty(nCopies);
	}
	
	public int getISBN()
	{
		return ISBN;
	}
	
	public String getDescription()
	{
		return description.get();
	}
	
	public SimpleStringProperty descriptionProperty()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description.set(description);
	}
	
	public String getSujet()
	{
		return sujet.get();
	}
	
	public SimpleStringProperty sujetProperty()
	{
		return sujet;
	}
	
	public void setSujet(String sujet)
	{
		this.sujet.set(sujet);
	}
	
	public int getnCopies()
	{
		return nCopies.get();
	}
	
	public SimpleIntegerProperty nCopiesProperty()
	{
		return nCopies;
	}
	
	public void setnCopies(int nCopies)
	{
		this.nCopies.set(nCopies);
	}
}
