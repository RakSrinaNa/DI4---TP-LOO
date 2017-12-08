package fr.polytech.hibernate.tp11.view.post;

import fr.polytech.hibernate.tp11.Controller;
import fr.polytech.hibernate.tp11.model.Post;
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
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
class PostTable extends SortedTableView<Post>
{
	private static final Pattern VALID_EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"); //http://www.ietf.org/rfc/rfc5322.txt
	
	PostTable(Controller controller)
	{
		super();
		setEditable(true);
		
		int colCount = 4;
		int padding = 2;
		
		TableColumn<Post, User> authorColumn = new TableColumn<>("Author");
		authorColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanObjectPropertyBuilder.<User>create().bean(cellData.getValue()).name("author").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleObjectProperty<>();
		});
		authorColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		TableColumn<Post, String> contentColumn = new TableColumn<>("Content");
		contentColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanStringPropertyBuilder.create().bean(cellData.getValue()).name("content").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleStringProperty();
		});
		contentColumn.setEditable(true);
		contentColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		contentColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		contentColumn.setOnEditCommit(evt -> {
			if(evt.getNewValue() != null && !evt.getNewValue().equals(""))
			{
				evt.getRowValue().setContent(evt.getNewValue());
				controller.onPostChanged(evt.getRowValue());
			}
			else //Refresh window
			{
				evt.getTableView().getColumns().get(0).setVisible(false);
				evt.getTableView().getColumns().get(0).setVisible(true);
			}
		});
		TableColumn<Post, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanStringPropertyBuilder.create().bean(cellData.getValue()).name("title").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleStringProperty();
		});
		titleColumn.setEditable(true);
		titleColumn.setCellFactory(list -> new StringTextFieldTableCell<>());
		titleColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		titleColumn.setOnEditCommit(evt -> {
			if(evt.getNewValue() != null && !evt.getNewValue().equals(""))
			{
				evt.getRowValue().setTitle(evt.getNewValue());
				controller.onPostChanged(evt.getRowValue());
			}
			else //Refresh window
			{
				evt.getTableView().getColumns().get(0).setVisible(false);
				evt.getTableView().getColumns().get(0).setVisible(true);
			}
		});
		TableColumn<Post, LocalDateTime> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanObjectPropertyBuilder.<LocalDateTime>create().bean(cellData.getValue()).name("password").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleObjectProperty<>();
		});
		dateColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		
		//noinspection unchecked
		getColumns().addAll(authorColumn, contentColumn, titleColumn, dateColumn);
		setList(controller.getPosts());
		Platform.runLater(this::resizeContent);
	}
	
	private void resizeContent()
	{
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	}
}
