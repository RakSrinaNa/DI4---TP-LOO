package fr.polytech.hibernate.tp10.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.*;
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
public class TypologyGroup implements Serializable
{
	@Id
	@OneToOne(targetEntity = FishGroup.class)
	private FishGroup group;
	
	@Id
	@OneToOne(targetEntity = Typology.class)
	private Typology typology;
	
	@Column
	private int count;
	
	public TypologyGroup(Typology typology, int count)
	{
		this.typology = typology;
		this.count = count;
	}
	
	public FishGroup getGroup()
	{
		return group;
	}
	
	public void setGroup(FishGroup group)
	{
		this.group = group;
	}
	
	public Typology getTypology()
	{
		return typology;
	}
	
	public void setTypology(Typology typology)
	{
		this.typology = typology;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
}
