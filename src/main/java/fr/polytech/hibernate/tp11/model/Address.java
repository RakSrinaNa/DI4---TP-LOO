package fr.polytech.hibernate.tp11.model;

import fr.polytech.hibernate.base.Controlled;
import javafx.beans.property.SimpleStringProperty;
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
	private static final long serialVersionUID = -7246599347595137343L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int ID;
	
	@Transient
	private SimpleStringProperty postalCode;
	
	@Transient
	private SimpleStringProperty city;
	
	@Transient
	private SimpleStringProperty street;
	
	@Transient
	private SimpleStringProperty state;
	
	@Transient
	private SimpleStringProperty country;
	
	public Address(String street, String city, String state, String postalCode, String country)
	{
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.postalCode = new SimpleStringProperty(postalCode);
		this.country = new SimpleStringProperty(country);
	}
	
	public Address(){}
	
	@Column
	public String getPostalCode()
	{
		return postalCode.get();
	}
	
	public SimpleStringProperty postalCodeProperty()
	{
		return postalCode;
	}
	
	public void setPostalCode(String postalCode)
	{
		this.postalCode.set(postalCode);
	}
	
	@Column
	public String getCity()
	{
		return city.get();
	}
	
	public SimpleStringProperty cityProperty()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city.set(city);
	}
	
	@Column
	public String getStreet()
	{
		return street.get();
	}
	
	public SimpleStringProperty streetProperty()
	{
		return street;
	}
	
	public void setStreet(String street)
	{
		this.street.set(street);
	}
	
	@Column
	public String getState()
	{
		return state.get();
	}
	
	public SimpleStringProperty stateProperty()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state.set(state);
	}
	
	@Column
	public String getCountry()
	{
		return country.get();
	}
	
	public SimpleStringProperty countryProperty()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country.set(country);
	}
}
