package fr.polytech.jdbc.td4;

import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import java.util.function.Consumer;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-09
 */
public class MainApplication extends ApplicationBase
{
	private BouquinsController controller;
	private BookTab bookTab;
	private CustomerTab customerTab;
	
	@Override
	public String getFrameTitle()
	{
		return "Bouquin";
	}
	
	@Override
	public void preInit() throws Exception
	{
		controller = new BouquinsController();
	}
	
	@Override
	public Consumer<Stage> getStageHandler()
	{
		return stage -> stage.setOnCloseRequest(evt -> controller.stop());
	}
	
	@Override
	public Consumer<Stage> getOnStageDisplayed() throws Exception
	{
		return null;
	}
	
	@Override
	public Parent createContent(Stage stage)
	{
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		
		bookTab = new BookTab(controller);
		customerTab = new CustomerTab(controller);
		
		tabPane.getTabs().addAll(bookTab, customerTab);
		
		return tabPane;
	}
}
