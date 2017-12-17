package fr.polytech.hibernate.tp7;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 07/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-07
 */
@Entity
@Table(name = "School")
public class School
{
	@Id
	@Column
	private String name;
	
	@Column
	private int studentCount;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "NSS")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Teacher> teachers;
	
	public School(String name, int studentCount)
	{
		setName(name);
		setStudentCount(studentCount);
		teachers = new HashSet<>();
	}
	
	public School()
	{
		teachers = new HashSet<>();
	}
	
	public void addTeacher(Teacher teacher)
	{
		teachers.add(teacher);
		teacher.setSchool(this);
	}
	
	public int getStudentCount()
	{
		return studentCount;
	}
	
	public void setStudentCount(int studentCount)
	{
		this.studentCount = studentCount;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
