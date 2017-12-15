package fr.polytech.hibernate.base;

import javafx.util.Pair;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * BlogController base, having basic functions.
 * <p>
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
	
	/**
	 * Constructor.
	 *
	 * @throws IllegalStateException If the connection couldn't be made.
	 */
	protected ControllerBase() throws IllegalStateException
	{
		sessionFactory = getSessionFactory();
		if(sessionFactory == null)
			throw new IllegalStateException("Couldn't create sessionFactory");
		session = sessionFactory.openSession();
	}
	
	/**
	 * Get all the elements of a class.
	 *
	 * @param klass The class to get.
	 * @param <T>   The class to get.
	 *
	 * @return A list of all the elements of the class.
	 */
	public <T> List<T> getElements(Class<T> klass)
	{
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> classQuery = criteriaBuilder.createQuery(klass);
		
		Root<T> root = classQuery.from(klass);
		
		CriteriaQuery<T> query = classQuery.select(root);
		return session.createQuery(query).getResultList();
	}
	
	/**
	 * Persist an object in the database.
	 *
	 * @param persistent The object to persist.
	 */
	public void persistObject(Object persistent)
	{
		if(persistent == null)
			return;
		Transaction tr = session.getTransaction();
		try
		{
			tr.begin();
			
			session.saveOrUpdate(persistent);
			
			tr.commit();
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
			tr.rollback();
		}
	}
	
	/**
	 * Update a persisted object.
	 *
	 * @param o The object to update.
	 */
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
	
	/**
	 * Remove an object from the database.
	 *
	 * @param o The object to remove.
	 */
	protected void deleteObject(Object o)
	{
		try
		{
			Transaction tr = session.getTransaction();
			tr.begin();
			
			session.delete(o);
			
			tr.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Make changes in a transaction.
	 *
	 * @param r The changes to do.
	 */
	@SuppressWarnings("WeakerAccess")
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
	
	/**
	 * Find elements matching a = condition.
	 *
	 * @param classType The class to find.
	 * @param column    The name of the column to apply the condition.
	 * @param value     The value to match.
	 * @param <T>       The class to find.
	 *
	 * @return The results.
	 */
	@SuppressWarnings("WeakerAccess")
	public <T> List<T> findEqual(Class<T> classType, String column, Object value)
	{
		ArrayList<Pair<String, Object>> constraints = new ArrayList<>();
		constraints.add(new Pair<>(column, value));
		return findEqual(classType, constraints);
	}
	
	/**
	 * Find elements matching several = condition.
	 *
	 * @param classType   The class to find.
	 * @param constraints The constraints to apply. The key is the column name, the key the value to match.
	 * @param <T>         The class to find.
	 *
	 * @return The results.
	 */
	@SuppressWarnings("WeakerAccess")
	public <T> List<T> findEqual(Class<T> classType, Collection<Pair<String, Object>> constraints)
	{
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> classQuery = criteriaBuilder.createQuery(classType);
		
		Root<T> root = classQuery.from(classType);
		List<Predicate> predicates = constraints.stream().map(constraint -> criteriaBuilder.equal(root.get(constraint.getKey()), constraint.getValue())).collect(Collectors.toList());
		
		CriteriaQuery<T> query = classQuery.select(root).where(predicates.toArray(new Predicate[]{}));
		return session.createQuery(query).getResultList();
	}
	
	/**
	 * Find elements matching a >= condition.
	 *
	 * @param classType The class to find.
	 * @param column    The name of the column to apply the condition.
	 * @param value     The value be greater to or equal.
	 * @param <T>       The class to find.
	 *
	 * @return The results.
	 */
	@SuppressWarnings("WeakerAccess")
	public <T> List<T> findGreaterOrEqual(Class<T> classType, String column, Number value)
	{
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> classQuery = criteriaBuilder.createQuery(classType);
		
		Root<T> root = classQuery.from(classType);
		CriteriaQuery<T> query = classQuery.where(criteriaBuilder.ge(root.get(column), value));
		
		return session.createQuery(query).getResultList();
	}
	
	/**
	 * Get all the classes inside a package.
	 *
	 * @param packageName The name of the package.
	 *
	 * @return The classes.
	 *
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
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
	
	/**
	 * Find the classes of a package inside a directory.
	 *
	 * @param directory   The directory to search in (recursively).
	 * @param packageName The name of the package.
	 *
	 * @return The classes found.
	 *
	 * @throws ClassNotFoundException
	 */
	private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException
	{
		List<Class> classes = new ArrayList<>();
		if(!directory.exists())
			return classes;
		for(File file : directory.listFiles())
			if(file.isDirectory())
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			else if(file.getName().endsWith(".class"))
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - ".class".length())));
		return classes;
	}
	
	/**
	 * Closes the controller.
	 */
	public void close()
	{
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * Get all the classes annotated with @Controlled.
	 *
	 * @return The classes found.
	 *
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Iterable<Class> getAnnotatedClasses() throws URISyntaxException, IOException, ClassNotFoundException
	{
		List<Class> classes = getClasses(getPackage());
		Iterator<Class> it = classes.iterator();
		while(it.hasNext())
			if(!it.next().isAnnotationPresent(Controlled.class))
				it.remove();
		return classes;
	}
	
	/**
	 * Get the name of the package where to find the model.
	 *
	 * @return The package name.
	 */
	protected abstract String getPackage();
	
	/**
	 * Build the session factory for hibernate.
	 *
	 * @return The session factory.
	 */
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