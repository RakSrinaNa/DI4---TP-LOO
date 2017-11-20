package fr.polytech.hibernate.tp10.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

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
	private int capacity;
	
	private String material;
	
	@Id
	@OneToOne(targetEntity = FishGroup.class)
	private FishGroup group;
	
	public Container(int capacity, String material)
	{
		this.capacity = capacity;
		this.material = material;
	}
	
	public Container(){}
	
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
		return group;
	}
	
	public void setGroup(FishGroup group)
	{
		this.group = group;
	}
}
