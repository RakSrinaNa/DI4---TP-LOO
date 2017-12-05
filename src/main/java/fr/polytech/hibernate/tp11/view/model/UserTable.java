package fr.polytech.hibernate.tp11.view.model;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.model.Address;
import fr.polytech.hibernate.tp11.model.User;
import fr.polytech.hibernate.tp11.view.utils.SortedTableView;
import fr.polytech.hibernate.tp11.view.utils.StringTextFieldTableCell;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
public class UserTable extends SortedTableView<User>
{
	public UserTable(Controller controller)
	{
		super();
		setEditable(true);
		
		int colCount = 5;
		int padding = 2;
		
		TableColumn<User, String> firstnameColumn = new TableColumn<>("Firstname");
		firstnameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
		firstnameColumn.setEditable(true);
		firstnameColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		firstnameColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		firstnameColumn.setOnEditCommit(evt -> controller.onUserChanged(evt.getRowValue()));
		TableColumn<User, String> lastnameColumn = new TableColumn<>("Lastname");
		lastnameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
		lastnameColumn.setEditable(true);
		lastnameColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		lastnameColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		lastnameColumn.setOnEditCommit(evt -> controller.onUserChanged(evt.getRowValue()));
		TableColumn<User, Address> addressColumn = new TableColumn<>("Address");
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
		addressColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		TableColumn<User, String> mailColumn = new TableColumn<>("Mail");
		mailColumn.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
		mailColumn.setEditable(true);
		mailColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		mailColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		mailColumn.setOnEditCommit(evt -> controller.onUserChanged(evt.getRowValue()));
		TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
		passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
		passwordColumn.setEditable(true);
		passwordColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		passwordColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		passwordColumn.setOnEditCommit(evt -> controller.onUserChanged(evt.getRowValue()));
		
		getColumns().addAll(firstnameColumn, lastnameColumn, addressColumn, mailColumn, passwordColumn);
		setList(controller.getUsers());
		Platform.runLater(this::resizeContent);
	}
	
	private void resizeContent()
	{
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	}
}
