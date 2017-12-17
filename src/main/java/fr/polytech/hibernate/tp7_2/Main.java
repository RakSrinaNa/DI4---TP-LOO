package fr.polytech.hibernate.tp7_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-09
 */
public class Main
{
	private static Session session;
	
	private static SessionFactory getSessionFactory()
	{
		try
		{
			return new Configuration().configure()
					.addAnnotatedClass(Address.class)
					.addAnnotatedClass(School.class)
					.addAnnotatedClass(Teacher.class)
					.buildSessionFactory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		
		School s = new School("Polytech", 0);
		s = addSchool(s);
		
		Teacher t = new Teacher("ZAIEOJOAIJ", "Jean", "Bon");
		addTeacher(s, t);
		
		session.close();
		sessionFactory.close();
	}
	
	private static School addSchool(School school)
	{
		try
		{
			Transaction tr = session.getTransaction();
			tr.begin();
			session.persist(school);
			tr.commit();
		}
		catch(Exception e)
		{
			school = session.find(School.class, school.getName());
		}
		return school;
	}
	
	private static void addTeacher(School sc, Teacher teacher)
	{
		try
		{
			Transaction tr = session.getTransaction();
			tr.begin();
			sc.addTeacher(teacher);
			tr.commit();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
