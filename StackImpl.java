
public class StackImpl extends AbstractStack implements Stack {

	private List list;
	
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
		return this.list.isEmpty();
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public void push(Object item) {
		this.list.add(0, item);
	}

	@Override
	public ReturnObject top() {
		return this.list.get(0);
	}

	@Override
	public ReturnObject pop() {
		return this.list.remove(0);
	}

}
