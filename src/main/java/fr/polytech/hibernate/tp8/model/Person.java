package fr.polytech.hibernate.tp8.model;

import javax.persistence.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-09
 */
@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person
{
	@Id
	@Column
	private String name;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@OneToOne
	private Address address;
	
	public Person(String name, String phone, String email)
	{
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public Person(){}
	
	public String getName()
	{
		return name;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
