package fr.polytech.jdbc.tdc;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Directory
{
	private final int id;
	private String name;
	private Directory parent;
	private final ArrayList<File> files;
	private final ArrayList<Tuple<Integer, String>> directories;
	
	/**
	 * Creates a Directory object.
	 *
	 * @param id          The id of the directory
	 * @param name        The name of the directory
	 * @param files       The list of the contained files
	 * @param directories The list of the contained directories
	 */
	public Directory(int id, String name, Directory parent, ArrayList<File> files, ArrayList<Tuple<Integer, String>> directories)
	{
		this.id = id;
		this.name = name;
		this.files = files;
		this.directories = directories;
		this.parent = parent;
	}
	
	public Directory getParent()
	{
		return parent;
	}
	
	public String getPath()
	{
		return (getParent() == null ? "" : getParent().getPath()) + name + "/";
	}
	
	public void setParent(Directory parent)
	{
		this.parent = parent;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public ArrayList<File> getFiles()
	{
		return files;
	}
	
	public ArrayList<Tuple<Integer, String>> getDirectories()
	{
		return directories;
	}
	
	public int getId()
	{
		return id;
	}
	
	@Override
	public String toString()
	{
		return String.format("DIR(ID: %d, Name: %s, Parent: %s)\nFiles: %s\nDirectories: %s", getId(), getName(), getParent() == null ? "" : getParent().getName(), getFiles().stream().map(File::toString).collect(Collectors.joining(", ", "[", "]")), getDirectories().stream().map(d -> "DIR(ID: " + d.getKey() + ", Name: " + d.getVal() + ")").collect(Collectors.joining(", ", "[", "]")));
	}
}
