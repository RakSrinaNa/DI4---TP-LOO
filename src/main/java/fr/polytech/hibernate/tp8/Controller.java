package fr.polytech.hibernate.tp8;

import fr.polytech.hibernate.tp8.model.Address;
import fr.polytech.hibernate.tp8.model.Person;
import fr.polytech.hibernate.tp8.model.Professor;
import fr.polytech.hibernate.tp8.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-09
 */
public class Controller
{
	private final Session session;
	private final SessionFactory sessionFactory;
	
	public void setPersonAddress(Person p, Address address)
	{
		try
		{
			Transaction tr = session.getTransaction();
			tr.begin();
			
			session.persist(address);
			p.setAddress(address);
			
			tr.commit();
		}
		catch(Exception e)
		{
		}
	}
	
	public List<Student> getStudentsWithAverageMore(int average)
	{
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Student> studentQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> root = studentQuery.from(Student.class);
		CriteriaQuery<Student> averageMarkQuery = studentQuery.where(criteriaBuilder.greaterThan(root.get("averageMark"), average));
		
		return session.createQuery(averageMarkQuery).getResultList();
	}
	
	private static SessionFactory getSessionFactory()
	{
		try
		{
			return new Configuration().configure()
					.addAnnotatedClass(Address.class)
					.addAnnotatedClass(Person.class)
					.addAnnotatedClass(Student.class)
					.addAnnotatedClass(Professor.class)
					.buildSessionFactory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public Controller() throws IllegalStateException
	{
		sessionFactory = getSessionFactory();
		if(sessionFactory == null)
			throw new IllegalStateException("Couldn't create sessionFactory");
		session = sessionFactory.openSession();
	}
	
	public void close()
	{
		session.close();
		sessionFactory.close();
	}
	
	public void addPerson(Person p)
	{
		try
		{
			Transaction tr = session.getTransaction();
			tr.begin();
			
			session.persist(p);
			
			tr.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
