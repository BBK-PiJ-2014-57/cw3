/**
 * Class which implements the interface SampleableList
 * @author lewispalmer
 *
 */
public class SampleableListImpl implements SampleableList {
	/**
	 * Creates an underlying list to perform functions on.
	 */
	private List list;
	
	public SampleableListImpl(List list)
	{
		this.list = list;
	}
	
	public SampleableListImpl()
	{
		this.list = new ArrayList();
	}
	
	public SampleableListImpl(int reqsize)
	{
		this.list = new ArrayList(reqsize);
	}
	
	public SampleableListImpl(Object[] data)
	{
		this.list = new ArrayList(data);
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public ReturnObject get(int index) {
		return list.get(index);
	}

	@Override
	public ReturnObject remove(int index) {
		return list.remove(index);
	}

	@Override
	public ReturnObject add(int index, Object item) {
		return list.add(index, item);
	}

	@Override
	public ReturnObject add(Object item) {
		return list.add(item);
	}

	@Override
	public SampleableList sample() {
		if(list.size()==0)
			return new SampleableListImpl(new ArrayList());
		else{
			ArrayList toret = new ArrayList(list.size());
			for(int i = 0; i<list.size(); i+=2)
			{
				toret.add(list.get(i).getReturnValue());
			}
			return new SampleableListImpl(toret);
		}
	}

}
