/**
 * A class which implements the class List.java.
 * @author lewispalmer
 *
 */
public class ArrayList implements List {
	
	//Initial size allows for easier Constructor
	public static final int defaultArraySize = 10;
	private int currsize;
	private Object[] elements;
	
	
	/**
	 * Constructor - creates an ArrayListImpl from a given array of Objects
	 * @param eles the objects to be put into the ArrayList
	 */
	public ArrayList(Object[] eles)
	{
		
		if(eles.length !=0 || eles == null)
		{
			this.currsize = eles.length;
			this.elements = new Object[this.currsize];
			for(int i = 0; i<this.currsize; i++)
			{
				this.elements[i] = eles[i];
			}
			}
		else
		{
			this.currsize = defaultArraySize;
			this.elements = new Object[defaultArraySize];
		}
	}
	
	
	/**
	 * Constructor - creates an empty ArrayList with a given max size
	 * @param reqsize the requested max size of the ArrayList
	 */
	public ArrayList(int reqsize)
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
	public ArrayList()
	{
		this.currsize = defaultArraySize;
		this.elements = new Object[defaultArraySize];
	}
	
	/**
	 * Copy Constructor - hopefully satisfies for Functional list. Just creates a new instance of the class with same fields.
	 * @param tocopy
	 */
	public ArrayList(ArrayList tocopy)
	{
		this.currsize = tocopy.currsize;
		this.elements = tocopy.elements.clone();
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

	@Override
	public ReturnObject get(int index) {
		ErrorMessage retmsg = FindErrorUtility.FindError(index, false, null, this);
		if(retmsg != ErrorMessage.NO_ERROR)
		{
			return new ReturnObjectImpl(retmsg, null);
		}
		return new ReturnObjectImpl(false, retmsg, this.elements[index]);
	}

	@Override
	public ReturnObject remove(int index) {
		ErrorMessage retmsg = FindErrorUtility.FindError(index, false, null, this);
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
		ArrayList temp = new ArrayList(this.elements);
		this.elements = new Object[this.currsize + defaultArraySize];
		for(int i = 0; i<temp.size(); i++)
		{
			this.elements[i] = temp.elements[i];
		}
		this.currsize += defaultArraySize;
	}

	@Override
	public ReturnObject add(int index, Object item) {
		ErrorMessage retmsg = FindErrorUtility.FindError(index, true, item, this);
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
			for(int i = this.size(); i>=index; i--)
			{
				this.elements[i+1] = this.elements[i];
			}
		}
		//Adds new element
		this.elements[index] = item;
		return new ReturnObjectImpl(retmsg, null);
	}

	@Override
	public ReturnObject add(Object item) {
		ErrorMessage retmsg = FindErrorUtility.FindError(this.size(), true, item, true, this);
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
