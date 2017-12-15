package fr.polytech.hibernate.tp11.view.post;

import fr.polytech.hibernate.tp11.BlogController;
import fr.polytech.hibernate.tp11.model.Keyword;
import fr.polytech.hibernate.tp11.model.Post;
import fr.polytech.hibernate.tp11.model.User;
import fr.polytech.hibernate.tp11.view.utils.SortedTableView;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 05/12/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-12-05
 */
class PostTable extends SortedTableView<Post>
{
	private final SimpleObjectProperty<Predicate<Post>> filterRule;
	
	PostTable(BlogController blogController, User user, ObjectProperty<LocalDate> dateProperty, ReadOnlyObjectProperty<Keyword> keywordProperty)
	{
		super();
		setEditable(false);
		filterRule = new SimpleObjectProperty<>(employee -> true);
		
		final SimpleObjectProperty<Predicate<Post>> dateFilter = new SimpleObjectProperty<>(check -> true);
		final SimpleObjectProperty<Predicate<Post>> keywordFilter = new SimpleObjectProperty<>(check -> true);
		InvalidationListener refreshFilters = observable -> filterRule.set(dateFilter.get().and(keywordFilter.get())); //Refresh the global filter when one of the sub rules change
		dateFilter.addListener(refreshFilters);
		keywordFilter.addListener(refreshFilters);
		
		dateProperty.addListener(((observable, oldValue, newValue) -> {
			if(newValue == null)
				dateFilter.set(check -> true);
			else
				dateFilter.set(check -> check.getDate().toLocalDate().equals(newValue));
		}));
		keywordProperty.addListener(((observable, oldValue, newValue) -> {
			if(newValue == null)
				dateFilter.set(check -> true);
			else
				dateFilter.set(check -> check.getKeywords().contains(newValue));
		}));
		
		int colCount = 5;
		int padding = 2;
		
		TableColumn<Post, User> authorColumn = new TableColumn<>("Author");
		authorColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanObjectPropertyBuilder.<User> create().bean(cellData.getValue()).name("author").build();
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
		contentColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
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
		titleColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		TableColumn<Post, LocalDateTime> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanObjectPropertyBuilder.<LocalDateTime> create().bean(cellData.getValue()).name("date").build();
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
		TableColumn<Post, List<Keyword>> keywordsColumn = new TableColumn<>("Keywords");
		keywordsColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanObjectPropertyBuilder.<LocalDateTime> create().bean(cellData.getValue()).name("keywords").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleObjectProperty<>();
		});
		keywordsColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		TableColumn<Post, List<Keyword>> linksColumn = new TableColumn<>("Links");
		linksColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanObjectPropertyBuilder.<LocalDateTime> create().bean(cellData.getValue()).name("links").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleObjectProperty<>();
		});
		linksColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		TableColumn<Post, List<Keyword>> imagesColumn = new TableColumn<>("Images");
		imagesColumn.setCellValueFactory(cellData -> {
			try
			{
				return JavaBeanObjectPropertyBuilder.<LocalDateTime> create().bean(cellData.getValue()).name("images").build();
			}
			catch(Exception e)
			{
				System.out.println("Error building table:");
				e.printStackTrace();
				System.exit(1);
			}
			return new SimpleObjectProperty<>();
		});
		imagesColumn.prefWidthProperty().bind(widthProperty().subtract(padding).divide(colCount));
		
		setRowFactory(tv -> {
			TableRow<Post> row = new TableRow<>();
			row.setOnMouseClicked(evt -> {
				if(evt.getButton() == MouseButton.SECONDARY)
				{
					ContextMenu contextMenu = new ContextMenu();
					MenuItem menuMore = new MenuItem("More infos");
					MenuItem menuModify = new MenuItem("Modify post");
					MenuItem menuDelete = new MenuItem("Delete post");
					
					menuMore.setOnAction(evt1 -> blogController.infosPost(this, row.getItem()));
					menuModify.setOnAction(evt1 -> blogController.modifyPost(evt, row.getItem()));
					menuDelete.setOnAction(evt1 -> blogController.deletePost(row.getItem()));
					
					contextMenu.getItems().add(menuMore);
					if(user.equals(row.getItem().getAuthor()))
					{
						contextMenu.getItems().add(menuModify);
						contextMenu.getItems().add(menuDelete);
					}
					contextMenu.show(this, evt.getScreenX(), evt.getScreenY());
				}
			});
			return row;
		});
		
		//noinspection unchecked
		getColumns().addAll(authorColumn, titleColumn, contentColumn, dateColumn, keywordsColumn, linksColumn, imagesColumn);
		setList(blogController.getPosts());
		Platform.runLater(this::resizeContent);
	}
	
	@Override
	public void setList(ObservableList<Post> list)
	{
		FilteredList<Post> filteredItems = new FilteredList<>(list);
		filteredItems.predicateProperty().bind(filterRule);
		super.setList(filteredItems);
	}
	
	private void resizeContent()
	{
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
	}
}
