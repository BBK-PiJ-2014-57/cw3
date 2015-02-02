/**
 * Class which Implements the ImproveStack interface which utilises the fact that 
 * ImprovedStack extends the Stack interface.
 * @author lewispalmer
 *
 */
public class ImprovedStackImpl implements ImprovedStack {

	//private field allows us to use all the methods from Stack.
	private Stack internalStack;
	
	/**
	 * Constructor - creates a ImprovesStack from a given List.
	 * @param list the list to be turned into an ImprovedStack
	 */
	public ImprovedStackImpl(List list)
	{
		this.internalStack = new StackImpl(list);
	}
	
	/**
	 * Constructor - creates an empty ImprovedStack
	 */
	public ImprovedStackImpl()
	{
		this.internalStack = new StackImpl();
	}
	
	@Override
	public boolean isEmpty() {
		return this.internalStack.isEmpty();
	}

	@Override
	public int size() {
		return this.internalStack.size();
	}

	@Override
	public void push(Object item) {
		this.internalStack.push(item);
	}

	@Override
	public ReturnObject top() {
		return this.internalStack.top();
	}

	@Override
	public ReturnObject pop() {
		return this.internalStack.pop();
	}

	@Override
	public ImprovedStack reverse() {
		ImprovedStackImpl reversed = new ImprovedStackImpl();
		int length = this.internalStack.size();
		for(int i=0; i<length; i++)
		{
			reversed.internalStack.push(this.internalStack.pop().getReturnValue());
		}
		return reversed;
	}

	@Override
	public void remove(Object object) {
		ImprovedStackImpl reversed = new ImprovedStackImpl();
		reversed = (ImprovedStackImpl)this.reverse();
		int length = reversed.size();
		//ImprovedStack is already empty thanks to the remove method.
		for(int i = 0; i < length; i++)
		{
			if(!reversed.internalStack.top().getReturnValue().equals(object))
			{
				this.internalStack.push(reversed.internalStack.pop().getReturnValue());
			}
			else
			{
				reversed.internalStack.pop();
			}
		}
	}

}
