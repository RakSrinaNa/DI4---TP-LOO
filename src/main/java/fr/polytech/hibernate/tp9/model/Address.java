package fr.polytech.hibernate.tp9.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 17/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-17
 */
@Entity
@Table
@Controlled
public class Address
{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int ID;
	
	@Column
	private String street1;
	
	@Column
	private String street2;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private String zip;
	
	public Address(String s1, String s2, String z, String c, String s)
	{
		street1 = s1;
		street2 = s2;
		zip = z;
		city = c;
		state = s;
	}
	
	public Address(){}
	
	public String getStreet1()
	{
		return street1;
	}
	
	public void setStreet1(String street1)
	{
		this.street1 = street1;
	}
	
	public String getStreet2()
	{
		return street2;
	}
	
	public void setStreet2(String street2)
	{
		this.street2 = street2;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	@Override
	public String toString()
	{
		return getStreet1() + ", " + getStreet2() + ", " + getZip() + " " + getCity() + ", " + getState();
	}
}
