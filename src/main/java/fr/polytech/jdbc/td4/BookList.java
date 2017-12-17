package fr.polytech.jdbc.td4;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-09
 */
public class BookList extends TableView<Book>
{
	public BookList(ObservableList<Book> bookList)
	{
		super(bookList);
		
		int colCount = 4;
		int padding = 2;
		
		setEditable(true);
		
		TableColumn<Book, Number> columnID = new TableColumn<>("ISBN");
		columnID.setCellValueFactory(value -> new SimpleIntegerProperty(value.getValue().getISBN()));
		columnID.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		
		TableColumn<Book, String> columnDescription = new TableColumn<>("Description");
		columnDescription.setEditable(true);
		columnDescription.setCellValueFactory(value -> value.getValue().descriptionProperty());
		columnDescription.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		
		TableColumn<Book, String> columnSujet = new TableColumn<>("Sujet");
		columnSujet.setEditable(true);
		columnSujet.setCellValueFactory(value -> value.getValue().sujetProperty());
		columnSujet.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		
		TableColumn<Book, Number> columnCopies = new TableColumn<>("NCopies");
		columnCopies.setEditable(true);
		columnCopies.setCellValueFactory(value -> value.getValue().nCopiesProperty());
		columnCopies.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		
		//noinspection unchecked
		getColumns().addAll(columnID, columnDescription, columnSujet, columnCopies);
		skinProperty().addListener((obs, oldSkin, newSkin) -> {
			final TableHeaderRow header = (TableHeaderRow) lookup("TableHeaderRow");
			header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
		});
	}
}
