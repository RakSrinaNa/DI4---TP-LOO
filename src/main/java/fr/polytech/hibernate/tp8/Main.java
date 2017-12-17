package fr.polytech.hibernate.tp8;

import fr.polytech.hibernate.tp8.model.Address;
import fr.polytech.hibernate.tp8.model.Person;
import fr.polytech.hibernate.tp8.model.Professor;
import fr.polytech.hibernate.tp8.model.Student;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-09
 */
public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		
		Person person = new Person("JeanBon", "01", "a@b.c");
		controller.addPerson(person);
		controller.setPersonAddress(person, new Address("24 Rue du pain", "NewCity", "NY", "567", "USA"));
		
		Student student = new Student("JeanMich", "02", "d@e.f", 3456789, 11);
		controller.addPerson(student);
		
		Professor professor = new Professor("JeanMoule", "04", "g@h.i", 3000);
		controller.addPerson(professor);
		
		
		for(Student s : controller.getStudentsWithAverageMore(10))
			System.out.println(s);
		
		controller.close();
	}
}
