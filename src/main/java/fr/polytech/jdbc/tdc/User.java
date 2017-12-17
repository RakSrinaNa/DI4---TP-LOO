package fr.polytech.jdbc.tdc;

public class User {
	private final int id;
	private final String username;
	
	/**
	 * Creates a User object.
	 * 
	 * @param id ID of the user
	 * @param username Name of the user
	 */
	public User(int id, String username) {
		this.id = id;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}
}
