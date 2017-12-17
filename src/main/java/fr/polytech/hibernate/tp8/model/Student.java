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
public class Student extends Person
{
	@Column
	private int studentNumber;
	
	@Column
	private double averageMark;
	
	public Student(String name, String phone, String email, int studentNumber, int averageMark)
	{
		super(name, phone, email);
		this.studentNumber = studentNumber;
		this.averageMark = averageMark;
	}
	
	public Student(){}
	
	public int getStudentNumber()
	{
		return studentNumber;
	}
	
	public void setStudentNumber(int studentNumber)
	{
		this.studentNumber = studentNumber;
	}
	
	public double getAverageMark()
	{
		return averageMark;
	}
	
	public void setAverageMark(double averageMark)
	{
		this.averageMark = averageMark;
	}
}
