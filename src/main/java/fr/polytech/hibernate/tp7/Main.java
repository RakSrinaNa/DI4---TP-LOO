package fr.polytech.hibernate.tp7;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 07/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-07
 */
@SuppressWarnings("Duplicates")
public class Main
{
	private static EntityManagerFactory schoolManager;
	private static EntityManagerFactory teacherManager;
	
	public static void main(String[] args)
	{
		schoolManager = Persistence.createEntityManagerFactory("School");
		teacherManager = Persistence.createEntityManagerFactory("Teacher");

		School s = new School("Polytech", 0);
		s = addSchool(s);

		Teacher t = new Teacher("ZAIEOJOAIJ", "Jean", "Bon");
		addTeacher(s, t);

		schoolManager.close();
		teacherManager.close();
	}
	
	private static School addSchool(School school)
	{
		EntityManager em = schoolManager.createEntityManager();
		try
		{
			em.getTransaction().begin();
			em.persist(school);
			em.getTransaction().commit();
		}
		catch(RollbackException e)
		{
			school = em.find(School.class, school.getName());
		}
		finally
		{
			em.close();
		}
		return school;
	}
	
	private static void addTeacher(School sc, Teacher teacher)
	{
		EntityManager em = schoolManager.createEntityManager();
		try
		{
			em.getTransaction().begin();
			sc.addTeacher(teacher);
			em.getTransaction().commit();
		}
		catch(RollbackException e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			em.close();
		}
	}
}
