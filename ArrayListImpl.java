
public class ArrayListImpl implements List {
	
	//Initial size allows for easier Constructor
	public static final int defaultArraySize = 10;
	private int currsize;
	private Object[] elements;
	
	//Constructor - creates an ArrayList of a specified array
	public ArrayListImpl(Object[] eles)
	{
		this.currsize = eles.length;
		this.elements = new Object[this.currsize];
		for(int i = 0; i<this.currsize; i++)
		{
			this.elements[i] = eles[i];
		}
	}
	
	//Constructor II - creates an ArrayList of a certain size
	public ArrayListImpl(int reqsize)
	{
		if(reqsize > 0)
		{
			this.currsize = reqsize;
			this.elements = new Object[this.currsize];
		}
	}
	
	//Constructor III - the lazy man, no array or specified size.
	public ArrayListImpl()
	{
		this.currsize = defaultArraySize;
		this.elements = new Object[defaultArraySize];
	}
	
	@Override
	public boolean isEmpty() {
		for(int i = 0; i<this.currsize; i++)
		{
			if(this.elements[i] != null)
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public int size() {
		int size = 0;
		for(int i = 0; i<this.currsize; i++)
		{
			if(this.elements[i] != null)
			{
				size++;
			}
		}
		return size;
	}
	
	//Little function to find the error message, based on action. To prevent code copying.
	private ErrorMessage FindError(int index, boolean adding, Object obj)
	{
		if(index < 0 || index > this.currsize)
		{
			return ErrorMessage.INDEX_OUT_OF_BOUNDS;
		}
		if(this.isEmpty() && !adding)
		{
			return ErrorMessage.EMPTY_STRUCTURE;
		}
		if(adding && obj == null)
		{
			return ErrorMessage.INVALID_ARGUMENT;
		}
		return ErrorMessage.NO_ERROR;
	}

	@Override
	public ReturnObject get(int index) {
		ErrorMessage retmsg = FindError(index, false, null);
		if(retmsg != ErrorMessage.NO_ERROR)
		{
			return new ReturnObjectImpl(retmsg, null);
		}
		return new ReturnObjectImpl(false, retmsg, this.elements[index]);
	}

	@Override
	public ReturnObject remove(int index) {
		ErrorMessage retmsg = FindError(index, false, null);
		if(retmsg != ErrorMessage.NO_ERROR)
		{
			return new ReturnObjectImpl(retmsg, null);
		}
		//Creates a copy of the element before removing
		Object tempObj = this.elements[index];
		for(int i = index; i<(this.size()-1); i++)
		{
			//Moves each element up the list
			this.elements[i] = this.elements[i+1];
		}
		//Sets the last element to null
		this.elements[this.size() - 1] = null;
		return new ReturnObjectImpl(false, retmsg, tempObj);
	}

	@Override
	public ReturnObject add(int index, Object item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnObject add(Object item) {
		// TODO Auto-generated method stub
		return null;
	}

}
