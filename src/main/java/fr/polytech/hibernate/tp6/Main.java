package fr.polytech.hibernate.tp6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 27/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-27
 */
public class Main
{
	private static EntityManagerFactory employerManager;
	
	public static void main(String[] args)
	{
		employerManager = Persistence.createEntityManagerFactory("Employer");
		
		Employer e = new Employer("Jean", "Bon", Date.valueOf(LocalDate.now()), 13.3);
		addEmployer(e);
		
		employerManager.close();
	}
	
	private static void addEmployer(Employer employer)
	{
		EntityManager em = employerManager.createEntityManager();
		em.getTransaction().begin();
		em.persist(employer);
		em.getTransaction().commit();
		em.close();
	}
}
