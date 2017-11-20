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
public class Container implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int ID;
	
	private int capacity;
	
	private String material;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = FishGroup.class)
	private List<FishGroup> group;
	
	public Container(int capacity, String material)
	{
		this.capacity = capacity;
		this.material = material;
		this.group = new LinkedList<>();
	}
	
	public Container()
	{
		this.group = new LinkedList<>();
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	
	public String getMaterial()
	{
		return material;
	}
	
	public void setMaterial(String material)
	{
		this.material = material;
	}
	
	public FishGroup getGroup()
	{
		return group.size() == 0 ? null : group.get(0);
	}
	
	public void setGroup(FishGroup group)
	{
		this.group.set(0, group);
	}
}
