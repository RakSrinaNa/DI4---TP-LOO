package fr.polytech.hibernate.tp9;

import fr.polytech.hibernate.tp9.model.Address;
import fr.polytech.hibernate.tp9.model.Company;
import fr.polytech.hibernate.tp9.model.Job;
import fr.polytech.hibernate.tp9.model.Person;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 17/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-17
 */
public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		
		Address address1 = new Address("987 Rue du pont", "par ici", "12345", "TheCity", "FL");
		Address address2 = new Address("097 Rue du pont", "par ici", "12345", "TheCity", "FL");
		Address address3 = new Address("456 Rue du pont", "par ici", "12345", "TheCity", "FL");
		controller.persistObject(address1);
		controller.persistObject(address2);
		controller.persistObject(address3);
		
		Company company = new Company("myCompany", address1);
		controller.persistObject(company);
		
		Job job1 = new Job(company, "AJob", 200, "AJ");
		controller.addJobToCompany(company, job1);
		
		Person person1 = new Person("Jean", "Moule", 'C', address2);
		controller.persistObject(person1);
		controller.setPersonJob(person1, job1);
		
		Person person2 = new Person("Jean", "Nouille", 'D', address3);
		controller.hire(company, person2, "Respo", 250);
		
		System.out.println(controller.findByMiddleInitial('C'));
		System.out.println(controller.findBySalary(200));
		System.out.println(controller.findBySalaryGreater(200));
		
		controller.close();
	}
}
