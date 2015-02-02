/**
 * Class which impelents the interface Stack, using the AbstractStack constructor and it's 
 * internal list to perform operation.
 * @author lewispalmer
 *
 */
public class StackImpl extends AbstractStack implements Stack {

	public StackImpl()
	{
		super(new ArrayList());
	}
	
	public StackImpl(List list)
	{
		super(list);
	}
	
	@Override
	public boolean isEmpty() {
		return this.internalList.isEmpty();
	}

	@Override
	public int size() {
		return this.internalList.size();
	}

	@Override
	public void push(Object item) {
		if(this.internalList.size() != 0)
		{
			this.internalList.add(0, item);
		}
		else
		{
			//List interface implementation only allow this method to add to empty lists.
			this.internalList.add(item);
		}
	}

	@Override
	public ReturnObject top() {
		return this.internalList.get(0);
	}

	@Override
	public ReturnObject pop() {
		return this.internalList.remove(0);
	}

}
