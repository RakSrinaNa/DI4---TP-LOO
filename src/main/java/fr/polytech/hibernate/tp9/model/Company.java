package fr.polytech.hibernate.tp9.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 17/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-17
 */
@Entity
@Table
@Controlled
public class Company
{
	@Id
	@Column
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Job.class)
	private List<Job> jobs;
	
	@OneToOne(targetEntity = Address.class)
	private Address address;
	
	public Company(String name, Address address)
	{
		jobs = new LinkedList<>();
		this.name = name;
		this.address = address;
	}
	
	public Company(){}
	
	public void addJob(Job j)
	{
		jobs.add(j);
	}
	
	public Address getAddress()
	{
		return address;
	}
	
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
	
	@Override
	public String toString()
	{
		return "Company " + getName() + " located at [" + getAddress() + "] with jobs: [" + jobs.stream().map(j -> "" + j.getID()).collect(Collectors.joining(", ")) + "]";
	}
}
