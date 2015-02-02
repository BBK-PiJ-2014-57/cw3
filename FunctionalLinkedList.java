
public class FunctionalLinkedList extends LinkedList implements FunctionalList {

	/**
	 * {@inheritDoc}
	 */
	public FunctionalLinkedList()
	{
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FunctionalLinkedList(Object[] toadd)
	{
		super(toadd);
	}
	
	/**
	 * {@inheritDoc}
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
