package fr.polytech.hibernate.tp10.model;

import fr.polytech.hibernate.base.Controlled;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 20/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-20
 */
@Entity
@Table
@PrimaryKeyJoinColumn(name = "species")
@Controlled
public class GroundTypology extends Typology
{
	@Column
	private String groundMaterial;
	
	public GroundTypology(String species, int waterLiters, String groundMaterial)
	{
		super(species, waterLiters);
		this.groundMaterial = groundMaterial;
	}
	
	public GroundTypology(){}
	
	public String getGroundMaterial()
	{
		return groundMaterial;
	}
	
	public void setGroundMaterial(String groundMaterial)
	{
		this.groundMaterial = groundMaterial;
	}
}
