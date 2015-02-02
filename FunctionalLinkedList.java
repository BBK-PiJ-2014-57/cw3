/**
 * Class which implements the FunctionalList Interface using the functions from LinkedList
 * @author lewispalmer
 *
 */
public class FunctionalLinkedList extends LinkedList implements FunctionalList {

	/**
	 * Creates an empty FunctionalLinkedList by calling the super class.
	 */
	public FunctionalLinkedList()
	{
		super();
	}
	
	/**
	 * Creates a FunctionalLinkedList with a given Object array by calling the super class.
	 */
	public FunctionalLinkedList(Object[] toadd)
	{
		super(toadd);
	}
	
	/**
	 * Copy constructor for the FunctionalLinkedList class.
	 */
	public FunctionalLinkedList(LinkedList copy)
	{
		super(copy);
	}
	
	@Override
	public ReturnObject head() {
		return new ReturnObjectImpl((ReturnObjectImpl)this.get(0));
	}

	@Override
	public FunctionalList rest() {
		FunctionalList copy = new FunctionalLinkedList(this);
		copy.remove(0);
		return copy;
	}

}
