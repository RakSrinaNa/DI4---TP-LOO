package fr.polytech.hibernate.tp11.view.utils;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 25/05/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-05-25
 */
public class StringTextFieldTableCell<T> extends TextFieldTableCell<T, String>
{
	/**
	 * Constructor.
	 */
	public StringTextFieldTableCell()
	{
		super(new DefaultStringConverter());
	}
	
	@Override
	public void updateItem(String item, boolean empty)
	{
		super.updateItem(item != null ? item.trim() : null, empty);
	}
}
