package fr.polytech.hibernate.tp7_2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Teacher.class)
	private List<Teacher> teachers;
	
	public School(String name, int studentCount)
	{
		setName(name);
		setStudentCount(studentCount);
		teachers = new ArrayList<>();
	}
	
	public School()
	{
		teachers = new ArrayList<>();
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
