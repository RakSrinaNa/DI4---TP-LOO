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
@Inheritance(strategy = InheritanceType.JOINED)
@Controlled
public class Typology implements Serializable
{
	@Id
	@Column
	private String species;
	
	private int waterLiters;
	
	public Typology(String species, int waterLiters)
	{
		this.species = species;
		this.waterLiters = waterLiters;
	}
	
	public Typology(){}
	
	public String getSpecies()
	{
		return species;
	}
	
	public void setSpecies(String species)
	{
		this.species = species;
	}
	
	public int getWaterLiters()
	{
		return waterLiters;
	}
	
	public void setWaterLiters(int waterLitters)
	{
		this.waterLiters = waterLitters;
	}
}
