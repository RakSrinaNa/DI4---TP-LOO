package fr.polytech.hibernate.tp9.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 17/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-17
 */
@Entity
@Table
@Controlled
public class Person implements Serializable
{
	@Id
	@Column
	private String firstName;
	
	@Id
	@Column
	private String lastName;
	
	@Column
	private char middleInitial;
	
	@OneToOne(targetEntity = Job.class)
	private Job job;
	
	@OneToOne(targetEntity = Address.class)
	private Address address;
	
	public Person(String first, String last, char middle, Address address)
	{
		firstName = first;
		lastName = last;
		middleInitial = middle;
		this.address = address;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public Job getJob()
	{
		return job;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public void setJob(Job job)
	{
		this.job = job;
	}
	
	public Person(){}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public char getMiddleInitial()
	{
		return middleInitial;
	}
	
	public void setMiddleInitial(char middleInitial)
	{
		this.middleInitial = middleInitial;
	}
	
	@Override
	public String toString()
	{
		return getFirstName() + " " + getLastName() + " (" + getMiddleInitial() + ") lives at [" + getAddress() + "]";
	}
}
