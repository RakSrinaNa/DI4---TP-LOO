package fr.polytech.jdbc.td4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.awt.print.Book;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 09/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-09
 */
public class BookTab extends Tab
{
	private final BouquinsController controller;
	private final ObservableList<Book> books = FXCollections.observableArrayList();
	
	public BookTab(BouquinsController controller)
	{
		super();
		setText("Bouqins");
		this.controller = controller;
		
		VBox root = new VBox();
		BookList bookList = new BookList(controller.getBookList());
		HBox buttons = new HBox();
		Button addCopy = new Button("Add copy");
		Button addBook = new Button("Add book");
		buttons.getChildren().addAll(addCopy, addBook);
		HBox.setHgrow(addCopy, Priority.ALWAYS);
		HBox.setHgrow(addBook, Priority.ALWAYS);
		root.getChildren().addAll(bookList, buttons);
		VBox.setVgrow(bookList, Priority.ALWAYS);
		setContent(root);
	}
}
