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
public class Job implements Serializable
{
	@OneToOne(targetEntity = Company.class)
	private Company company;
	
	@OneToOne(targetEntity = Person.class)
	private Person person;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int ID;
	
	@Column
	private String title;
	
	@Column
	private float salary;
	
	@Column
	private String employeeId;
	
	public Job(Company c, String title, float salary, String employeeId)
	{
		this.company = c;
		this.title = title;
		this.salary = salary;
		this.employeeId = employeeId;
	}
	
	public Job(){}
	
	public Company getCompany()
	{
		return company;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public Person getPerson()
	{
		return person;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setCompany(Company company)
	{
		this.company = company;
	}
	
	public void setPerson(Person person)
	{
		this.person = person;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public float getSalary()
	{
		return salary;
	}
	
	public void setSalary(float salary)
	{
		this.salary = salary;
	}
	
	public String getEmployeeId()
	{
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}
	
	@Override
	public String toString()
	{
		return getPerson() + " is working as " + getTitle() + " and have a salary of " + salary;
	}
}
