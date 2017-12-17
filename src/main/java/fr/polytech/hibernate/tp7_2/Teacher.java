package fr.polytech.hibernate.tp7_2;

import javax.persistence.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 07/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-07
 */
@Entity
@Table(name = "Teacher")
public class Teacher
{
	@Id
	@Column(name = "NSS")
	private String NSS;
	
	@Column
	private String lastname;
	
	@Column
	private String firstname;
	
	@OneToOne(targetEntity = School.class)
	private School school;
	
	@OneToOne(targetEntity = Address.class)
	private Address address;
	
	public Teacher(String NSS, String firstname, String lastname)
	{
		setNSS(NSS);
		setFirstname(firstname);
		setLastname(lastname);
	}
	
	public Teacher(){}
	
	public String getNSS()
	{
		return NSS;
	}
	
	public void setNSS(String NSS)
	{
		this.NSS = NSS;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	
	public void setSchool(School school)
	{
		this.school = school;
	}
}
