package fr.polytech.hibernate.tp9;

import fr.polytech.hibernate.base.ControllerBase;
import fr.polytech.hibernate.tp9.model.Company;
import fr.polytech.hibernate.tp9.model.Job;
import fr.polytech.hibernate.tp9.model.Person;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-09
 */
@SuppressWarnings("Duplicates")
public class Controller extends ControllerBase
{
	public void addJobToCompany(Company c, Job j)
	{
		j.setCompany(c);
		persistObject(j);
		
		makeChanges(() -> c.addJob(j));
	}
	
	public void setPersonJob(Person p, Job j)
	{
		makeChanges(() -> {
			p.setJob(j);
			j.setPerson(p);
		});
	}
	
	public Job hire(Company company, Person person, String title, float salary)
	{
		persistObject(person);
		byte[] ID = new byte[15];
		ThreadLocalRandom.current().nextBytes(ID);
		Job j = new Job(company, title, salary, new String(ID));
		persistObject(j);
		setPersonJob(person, j);
		return j;
	}
	
	public List<Person> findByMiddleInitial(char middle)
	{
		return findEqual(Person.class, "middleInitial", middle);
	}
	
	public List<Job> findBySalary(float salary)
	{
		return findEqual(Job.class, "salary", salary);
	}
	
	public List<Job> findBySalaryGreater(float salary)
	{
		return findGreaterOrEqual(Job.class, "salary", salary);
	}
	
	@Override
	protected String getPackage()
	{
		return "fr.polytech.hibernate.tp9.model";
	}
}