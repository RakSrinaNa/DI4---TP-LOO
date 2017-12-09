package fr.polytech.hibernate.tp11.view.user;

import fr.polytech.hibernate.tp11.model.User;
import fr.polytech.hibernate.tp11.view.utils.StringTextFieldTableCell;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 25/05/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-05-25
 */
public class PasswordTextFieldTableCell extends StringTextFieldTableCell<User>
{
	private final User user;
	
	/**
	 * Constructor.
	 */
	public PasswordTextFieldTableCell(User user)
	{
		super();
		this.user = user;
	}
	
	@Override
	public void updateItem(String item, boolean empty)
	{
		super.updateItem(item != null ? (user.equals(getTableRow().getItem()) ? item.trim() : "<hidden>") : null, empty);
	}
}
