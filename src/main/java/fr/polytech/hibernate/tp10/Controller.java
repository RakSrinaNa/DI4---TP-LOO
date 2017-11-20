package fr.polytech.hibernate.tp10;

import fr.polytech.hibernate.base.ControllerBase;
import fr.polytech.hibernate.tp10.model.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 20/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-20
 */
public class Controller extends ControllerBase
{
	public boolean setContainerToGroup(Container c, FishGroup g)
	{
		if(c.getGroup() != null)
			return false;
		makeChanges(() -> {
			c.setGroup(g);
			g.setContainer(c);
		});
		return true;
	}
	
	public void setClientAddress(Client c, Address a)
	{
		persistObject(c);
		persistObject(a);
		makeChanges(() -> c.setAddress(a));
	}
	
	public void addClientFishGroup(Client c, FishGroup g)
	{
		persistObject(c);
		persistObject(g);
		for(TypologyGroup tg : g.getGroups())
		{
			persistObject(tg.getTypology());
			persistObject(tg);
		}
		makeChanges(() -> c.addFishGroup(g));
	}
	
	public void addTypology(Typology t)
	{
		persistObject(t);
	}
	
	public void addContainer(Container c)
	{
		persistObject(c);
	}
	
	@Override
	protected String getPackage()
	{
		return "fr.polytech.hibernate.tp10.model";
	}
}
