/**
 * Class which implements the FunctionalList Interface using the functions from ArrayList
 * @author lewispalmer
 *
 */
public class FunctionalArrayList extends ArrayList implements FunctionalList {

	/**
	 * {@inheritDoc}
	 */
	public FunctionalArrayList()
	{
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FunctionalArrayList(int reqsize)
	{
		super(reqsize);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FunctionalArrayList(Object[] toadd)
	{
		super(toadd);
	}
	
	@Override
	public ReturnObject head() {
		return new ReturnObjectImpl((ReturnObjectImpl)this.get(0));
	}

	@Override
	public FunctionalList rest() {
		FunctionalList copy = this.copy();
		copy.remove(0);
		return copy;
	}
	
	/**
	 * Function to make a copy of the FunctionalList - to prevent any changes when adding/removing items.
	 * @return the new Functional List
	 */
	private FunctionalList copy(){
		FunctionalList copy = new FunctionalArrayList(this.size());
		for(int i = 0; i<this.size(); i++)
		{
			copy.add(this.get(i).getReturnValue());
		}
		return copy;
	}
	
	

}
