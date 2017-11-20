package fr.polytech.hibernate.tp10.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
import java.io.Serializable;
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
public class FishGroup implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int ID;
	
	@Column
	private int mealPerDay;
	
	@OneToOne(targetEntity = Container.class)
	private Container container;
	
	@OneToOne(targetEntity = Client.class)
	private Client owner;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = TypologyGroup.class)
	private List<TypologyGroup> groups;
	
	public FishGroup(int mealPerDay, Client owner)
	{
		this.mealPerDay = mealPerDay;
		this.owner = owner;
		this.groups = new LinkedList<>();
	}
	
	public FishGroup()
	{
		this.groups = new LinkedList<>();
	}
	
	public int getMealPerDay()
	{
		return mealPerDay;
	}
	
	public void setMealPerDay(int mealPerDay)
	{
		this.mealPerDay = mealPerDay;
	}
	
	public Container getContainer()
	{
		return container;
	}
	
	public void setContainer(Container container)
	{
		this.container = container;
	}
	
	public Client getOwner()
	{
		return owner;
	}
	
	public void setOwner(Client owner)
	{
		this.owner = owner;
	}
	
	public List<TypologyGroup> getGroups()
	{
		return groups;
	}
	
	public void setGroups(List<TypologyGroup> groups)
	{
		this.groups = groups;
	}
}
