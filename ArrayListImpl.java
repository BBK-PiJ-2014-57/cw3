/**
 * A class which implements the class List.java.
 * @author lewispalmer
 *
 */
public class ArrayListImpl implements List {
	
	//Initial size allows for easier Constructor
	public static final int defaultArraySize = 10;
	private int currsize;
	private Object[] elements;
	
	
	/**
	 * Constructor - creates an ArrayListImpl from a given array of Objects
	 * @param eles the objects to be put into the ArrayList
	 */
	public ArrayListImpl(Object[] eles)
	{
		this.currsize = eles.length;
		this.elements = new Object[this.currsize];
		for(int i = 0; i<this.currsize; i++)
		{
			this.elements[i] = eles[i];
		}
	}
	
	
	/**
	 * Constructor - creates an empty ArrayList with a given max size
	 * @param reqsize the requested max size of the ArrayList
	 */
	public ArrayListImpl(int reqsize)
	{
		if(reqsize > 0)
		{
			this.currsize = reqsize;
			this.elements = new Object[this.currsize];
		}
	}
	
	/**
	 * Constructor - creates a ArrayList with default maximum size
	 */
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
	
	/**
	 * Function finds the appropriate Error message based on the operation and ArrayList
	 * @param index the index that is trying to be accessed or added
	 * @param adding whether the operation is adding to the list 
	 * @param obj the Object to be added to the ArrayList
	 * @param addtoend whether we are trying to add to the end of the 
	 * @return the appropriate Error Message
	 */
	private ErrorMessage FindError(int index, boolean adding, Object obj, boolean addtoend)
	{
		if(this.isEmpty() && !adding)
		{
			return ErrorMessage.EMPTY_STRUCTURE;
		}
		if(index < 0 || (index >=this.size() && !addtoend))
		{
			return ErrorMessage.INDEX_OUT_OF_BOUNDS;
		}
		if(adding && obj == null)
		{
			return ErrorMessage.INVALID_ARGUMENT;
		}
		return ErrorMessage.NO_ERROR;
	}
	
	/**
	 * Overloading FindError for functions that aren't adding to the end of this list.
	 * @param index the index that is trying to be accessed or added
	 * @param adding whether this function is adding elements
	 * @param obj the object to be added
	 * @return appropriate ErrorMessage
	 */
	private ErrorMessage FindError(int index, boolean adding, Object obj)
	{
		return FindError(index, adding, obj, false);
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
	
	/**
	 * Increases the size of the ArrayListImpl array size by the default Array Size.
	 */
	private void IncreaseArraySize()
	{
		ArrayListImpl temp = new ArrayListImpl(this.elements);
		this.elements = new Object[this.currsize + defaultArraySize];
		for(int i = 0; i<temp.size(); i++)
		{
			this.elements[i] = temp.elements[i];
		}
	}

	@Override
	public ReturnObject add(int index, Object item) {
		ErrorMessage retmsg = FindError(index, true, item);
		if(retmsg != ErrorMessage.NO_ERROR)
		{
			return new ReturnObjectImpl(retmsg, item);
		}
		//Check if we need to increase the size of the array!
		if(this.currsize < this.size()+1)
		{
			IncreaseArraySize();
		}
		if(this.elements[index]!=null)
		{
			//Shifts all the elements down one, if needed, to make space for the new item.
			for(int i = this.currsize; i>index; i--)
			{
				this.elements[i] = this.elements[i-1];
			}
		}
		//Adds new element
		this.elements[index] = item;
		return new ReturnObjectImpl(retmsg, null);
	}

	@Override
	public ReturnObject add(Object item) {
		ErrorMessage retmsg = FindError(this.size(), true, item, true);
		if(retmsg != ErrorMessage.NO_ERROR)
			return new ReturnObjectImpl(retmsg, item);
		//If there isn't space for the new item, get more space
		if(this.size() == this.currsize)
			IncreaseArraySize();
		//Add item to the end of the list
		this.elements[this.size()] = item;
		return new ReturnObjectImpl(retmsg, null);
	}

}
