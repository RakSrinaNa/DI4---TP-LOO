package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 07/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-07
 */
@Entity
@Controlled
@Access(value = AccessType.PROPERTY)
public class Address implements Serializable
{
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private static final long serialVersionUID = -7246599347595137343L;
	
	private int ID;
	
	private String postalCode;
	
	private String city;
	
	private String street;
	
	private String state;
	
	private String country;
	
	public Address(String street, String city, String state, String postalCode, String country)
	{
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	public Address(){}
	
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
	
	public String getPostalCode()
	{
		return postalCode;
	}
	
	public void setPostalCode(String postalCode)
	{
		String old = this.postalCode;
		this.postalCode = postalCode;
		pcs.firePropertyChange("postalCode", old, postalCode);
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		String old = this.city;
		this.city = city;
		pcs.firePropertyChange("city", old, city);
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setStreet(String street)
	{
		String old = this.street;
		this.street = street;
		pcs.firePropertyChange("street", old, street);
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		String old = this.state;
		this.state = state;
		pcs.firePropertyChange("state", old, state);
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		String old = this.country;
		this.country = country;
		pcs.firePropertyChange("country", old, country);
	}
	
	@Override
	public String toString()
	{
		return getStreet() + ", " + getPostalCode() + " " + getCity() + ", " + getState() + " " + getCountry();
	}
}
