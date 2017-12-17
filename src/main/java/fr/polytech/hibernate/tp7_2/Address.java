package fr.polytech.hibernate.tp7_2;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 07/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-07
 */
@Entity
@Table(name = "Address")
public class Address implements Serializable
{
	@Id
	@Column
	private String postalCode;

	@Id
	@Column
	private String city;
	
	@Id
	@Column
	private String street;
	
	@Id
	@Column
	private int streetNumber;
	
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
	
	public int getStreetNumber()
	{
		return streetNumber;
	}
	
	public void setStreetNumber(int streetNumber)
	{
		this.streetNumber = streetNumber;
	}
}
