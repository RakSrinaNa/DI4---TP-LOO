package fr.polytech.hibernate.tp8.model;

import javax.persistence.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-09
 */
@Entity
@Table
@PrimaryKeyJoinColumn(name = "name")
public class Professor extends Person
{
	@Column
	private double salary;
	
	public Professor(String name, String phone, String email, int salary)
	{
		super(name, phone, email);
		this.salary = salary;
	}
	
	public Professor(){}
}
