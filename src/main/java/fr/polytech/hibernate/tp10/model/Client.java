package fr.polytech.hibernate.tp10.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 20/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-20
 */
@Entity
@Table
@Controlled
public class Client
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = FishGroup.class)
	private List<FishGroup> groups;
	
	@OneToOne(targetEntity = Address.class)
	private Address address;
	
	@Column
	private String phone;
	
	public Client(String name, String phone)
	{
		this.name = name;
		this.phone = phone;
		this.groups = new LinkedList<>();
	}
	
	public Client()
	{
		this.groups = new LinkedList<>();
	}
	
	public void addFishGroup(FishGroup fishGroup)
	{
		groups.add(fishGroup);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<FishGroup> getGroups()
	{
		return groups;
	}
	
	public void setGroups(List<FishGroup> groups)
	{
		this.groups = groups;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public int getID()
	{
		return ID;
	}
}
