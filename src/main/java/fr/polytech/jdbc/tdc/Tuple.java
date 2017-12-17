package fr.polytech.jdbc.tdc;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 20/10/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-10-20
 */
public class Tuple<K, V>
{
	private final K key;
	private final V val;
	
	public Tuple(K key, V value)
	{
		this.key = key;
		this.val = value;
	}
	
	public K getKey()
	{
		return key;
	}
	
	public V getVal()
	{
		return val;
	}
}
