package fr.polytech.jdbc.tdc;

public class File {
	private final int id;
	private String name;
	private Directory parent;
	private final long size;
	
	/**
	 * Creates a File object.
	 * 
	 * @param id The id of the file
	 * @param name The name of the file
	 * @param parent The parent directory
	 * @param size The size of the file
	 */
	public File(int id, String name, Directory parent, long size) {
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.size = size;
	}

	public String getName() {
		return name;
	}
	
	public String getPath()
	{
		return parent.getPath() + getName();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Directory getParent() {
		return parent;
	}

	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public long getSize() {
		return size;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString()
	{
		return String.format("FILE(ID: %d, Name: %s, Parent: %s, Size %d)", getId(), getName(), getParent().getName(), getSize());
	}
}
