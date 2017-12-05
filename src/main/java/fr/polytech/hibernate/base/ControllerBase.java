package fr.polytech.hibernate.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-09
 */
@SuppressWarnings("Duplicates")
public abstract class ControllerBase
{
	private final Session session;
	private final SessionFactory sessionFactory;
	
	protected ControllerBase() throws IllegalStateException
	{
		sessionFactory = getSessionFactory();
		if(sessionFactory == null)
			throw new IllegalStateException("Couldn't create sessionFactory");
		session = sessionFactory.openSession();
	}
	
	public <T> List<T> getElements(Class<T> klass)
	{
		return session.createCriteria(klass).list();
	}
	
	public void persistObject(Object persistant)
	{
		if(persistant == null)
			return;
		Transaction tr = session.getTransaction();
		try
		{
			tr.begin();
			
			session.saveOrUpdate(persistant);
			
			tr.commit();
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e .getMessage());
			tr.rollback();
		}
	}
	
	public <T> List<T> getAllObject(Class<T> klass)
	{
		List<T> list = new ArrayList<>();
		try
		{
			list.addAll(session.createCriteria(klass).list());
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e .getMessage());
		}
		return list;
	}
	
	protected void updateObject(Object o)
	{
		try
		{
			Transaction tr = session.getTransaction();
			tr.begin();
			
			session.saveOrUpdate(o);
			
			tr.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void makeChanges(Runnable r)
	{
		try
		{
			Transaction tr = session.getTransaction();
			tr.begin();
			
			r.run();
			
			tr.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public <T> List<T> findEqual(Class<T> classType, String column, Object value)
	{
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> classQuery = criteriaBuilder.createQuery(classType);
		
		Root<T> root = classQuery.from(classType);
		CriteriaQuery<T> query = classQuery.where(criteriaBuilder.equal(root.get(column), value));
		
		return session.createQuery(query).getResultList();
	}
	
	public <T> List<T> findGreaterOrEqual(Class<T> classType, String column, Number value)
	{
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> classQuery = criteriaBuilder.createQuery(classType);
		
		Root<T> root = classQuery.from(classType);
		CriteriaQuery<T> query = classQuery.where(criteriaBuilder.ge(root.get(column), value));
		
		return session.createQuery(query).getResultList();
	}
	
	private List<Class> getClasses(String packageName) throws ClassNotFoundException, IOException, URISyntaxException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<>();
		while(resources.hasMoreElements())
		{
			URL resource = resources.nextElement();
			URI uri = new URI(resource.toString());
			dirs.add(new File(uri.getPath()));
		}
		List<Class> classes = new ArrayList<>();
		for(File directory : dirs)
			classes.addAll(findClasses(directory, packageName));
		
		return classes;
	}
	
	private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException
	{
		List<Class> classes = new ArrayList<>();
		if (!directory.exists())
			return classes;
		for (File file : directory.listFiles())
			if (file.isDirectory())
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			else if (file.getName().endsWith(".class"))
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - ".class".length())));
		return classes;
	}
	
	public void close()
	{
		session.close();
		sessionFactory.close();
	}
	
	private Iterable<Class> getAnnotatedClasses() throws URISyntaxException, IOException, ClassNotFoundException
	{
		List<Class> classes = getClasses(getPackage());
		Iterator<Class> it = classes.iterator();
		while(it.hasNext())
			if(!it.next().isAnnotationPresent(Controlled.class))
				it.remove();
		return classes;
	}
	
	protected abstract String getPackage();
	
	private SessionFactory getSessionFactory()
	{
		try
		{
			Configuration configuration = new Configuration().configure();
			for(Class c : getAnnotatedClasses())
			{
				System.out.println("Adding class " + c.getName() + " to the model.");
				configuration.addAnnotatedClass(c);
			}
			return configuration.buildSessionFactory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}