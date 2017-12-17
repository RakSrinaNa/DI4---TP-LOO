package fr.polytech.hibernate.tp6;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 27/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-27
 */
@Entity
@Table(name = "Employer")
public class Employer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "Firstname")
	private String firstname;
	
	@Column(name = "Lastname")
	private String lastname;
	
	@Column(name = "Birth")
	private Date birthdate;
	
	@Column(name = "Salary")
	private double salary;
	
	public Employer(String firstname, String lastname, Date birthdate, double salary)
	{
		setFirstname(firstname);
		setLastname(lastname);
		setBirthdate(birthdate);
		setSalary(salary);
	}
	
	protected Employer()
	{}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	
	public Date getBirthdate()
	{
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
}
