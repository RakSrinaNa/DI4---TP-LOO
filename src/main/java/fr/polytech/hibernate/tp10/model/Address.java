package fr.polytech.hibernate.tp10.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 07/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-07
 */
@Entity
@Table
@Controlled
public class Address implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int ID;
	
	@Column
	private String postalCode;
	
	@Column
	private String city;
	
	@Column
	private String street;
	
	@Column
	private String state;
	
	@Column
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
	
	public String getPostalCode()
	{
		return postalCode;
	}
	
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
}
