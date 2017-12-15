package fr.polytech.hibernate.tp11.view.user;

import fr.polytech.hibernate.tp11.BlogController;
import fr.polytech.hibernate.tp11.model.Address;
import fr.polytech.hibernate.tp11.model.User;
import fr.polytech.hibernate.tp11.view.utils.SortedTableView;
import fr.polytech.hibernate.tp11.view.utils.StringTextFieldTableCell;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.regex.Pattern;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
class UserTable extends SortedTableView<User>
{
	private static final Pattern VALID_EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"); //http://www.ietf.org/rfc/rfc5322.txt
	
	UserTable(BlogController blogController, User user)
	{
		super();
		setEditable(true);
		//getStylesheets().add("/jfx/table.css");
		//setBackground(new Background(new BackgroundImage(MainApplication.getIcon("background.png"), BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		int colCount = 6;
		int padding = 2;
		
		TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
		usernameColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanStringPropertyBuilder.create().bean(cellData.getValue()).name("username").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleStringProperty();
		});
		usernameColumn.setEditable(true);
		usernameColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		usernameColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		usernameColumn.setOnEditCommit(evt -> {
			if(evt.getRowValue().equals(user) && evt.getNewValue() != null && !evt.getNewValue().equals(""))
			{
				evt.getRowValue().setUsername(evt.getNewValue());
				blogController.onUserChanged(evt.getRowValue());
			}
			else //Refresh window
			{
				evt.getTableView().getColumns().get(0).setVisible(false);
				evt.getTableView().getColumns().get(0).setVisible(true);
			}
		});
		TableColumn<User, String> firstnameColumn = new TableColumn<>("Firstname");
		firstnameColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanStringPropertyBuilder.create().bean(cellData.getValue()).name("firstname").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleStringProperty();
		});
		firstnameColumn.setEditable(true);
		firstnameColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		firstnameColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		firstnameColumn.setOnEditCommit(evt -> {
			if(evt.getRowValue().equals(user) && evt.getNewValue() != null && !evt.getNewValue().equals(""))
			{
				evt.getRowValue().setFirstname(evt.getNewValue());
				blogController.onUserChanged(evt.getRowValue());
			}
			else //Refresh window
			{
				evt.getTableView().getColumns().get(0).setVisible(false);
				evt.getTableView().getColumns().get(0).setVisible(true);
			}
		});
		TableColumn<User, String> lastnameColumn = new TableColumn<>("Lastname");
		lastnameColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanStringPropertyBuilder.create().bean(cellData.getValue()).name("lastname").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleStringProperty();
		});
		lastnameColumn.setEditable(true);
		lastnameColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		lastnameColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		lastnameColumn.setOnEditCommit(evt -> {
			if(evt.getRowValue().equals(user) && evt.getNewValue() != null && !evt.getNewValue().equals(""))
			{
				evt.getRowValue().setLastname(evt.getNewValue());
				blogController.onUserChanged(evt.getRowValue());
			}
			else //Refresh window
			{
				evt.getTableView().getColumns().get(0).setVisible(false);
				evt.getTableView().getColumns().get(0).setVisible(true);
			}
		});
		TableColumn<User, Address> addressColumn = new TableColumn<>("Address");
		addressColumn.setCellValueFactory(cellData -> {
			try
			{
				//noinspection unchecked
				return JavaBeanObjectPropertyBuilder.create().bean(cellData.getValue()).name("address").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleObjectProperty<>();
		});
		addressColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		TableColumn<User, String> mailColumn = new TableColumn<>("Mail");
		mailColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanStringPropertyBuilder.create().bean(cellData.getValue()).name("mail").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleStringProperty();
		});
		mailColumn.setEditable(true);
		mailColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		mailColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		mailColumn.setOnEditCommit(evt -> {
			if(evt.getNewValue() != null && evt.getRowValue().equals(user) && !evt.getNewValue().equals("") && VALID_EMAIL.matcher(evt.getNewValue()).matches())
			{
				evt.getRowValue().setMail(evt.getNewValue());
				blogController.onUserChanged(evt.getRowValue());
			}
			else //Refresh window
			{
				evt.getTableView().getColumns().get(0).setVisible(false);
				evt.getTableView().getColumns().get(0).setVisible(true);
			}
		});
		TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
		passwordColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanStringPropertyBuilder.create().bean(cellData.getValue()).name("password").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleStringProperty();
		});
		passwordColumn.setEditable(true);
		passwordColumn.setCellFactory(list -> new PasswordTextFieldTableCell(user));
		passwordColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		passwordColumn.setOnEditCommit(evt -> {
			if(evt.getNewValue() != null && evt.getRowValue().equals(user) && !evt.getNewValue().equals(""))
			{
				evt.getRowValue().setPassword(evt.getNewValue());
				blogController.onUserChanged(evt.getRowValue());
			}
			else //Refresh window
			{
				evt.getTableView().getColumns().get(0).setVisible(false);
				evt.getTableView().getColumns().get(0).setVisible(true);
			}
		});
		
		//noinspection unchecked
		getColumns().addAll(usernameColumn, firstnameColumn, lastnameColumn, addressColumn, mailColumn, passwordColumn);
		setList(blogController.getUsers());
		Platform.runLater(this::resizeContent);
	}
	
	private void resizeContent()
	{
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	}
}
