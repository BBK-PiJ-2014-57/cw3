
public class LinkedList implements List {

	private ListNode front;//Holder for the top of the list
	
	/**
	 * Constructor - creating an empty Linked list.
	 */
	public LinkedList()
	{
		this.front = null;
	}
	
	/**
	 * Constructor - should make JUnit tests easier for Parameterization.
	 * @param toinput the collection to be added to the LinkedList.
	 */
	public LinkedList(Object[] toinput)
	{
		if(toinput.length==0)
			this.front = null;
		else{
			for(int i = 0; i<toinput.length; i++)
			{
				this.add(toinput[i]);
			}
		}
	}
	
	/**
	 * Copy Constructor - hopefully satisfies for Functional list. Just creates a new instance of the class with same fields.
	 * @param tocopy
	 */
	public LinkedList(LinkedList tocopy)
	{
		this.front = null;
		if(tocopy.front!=null)
		{
			this.front = new ListNode(tocopy.front);
			ListNode copytemp = tocopy.front;
			ListNode temp = this.front;
			while(copytemp.next!=null)
			{
				temp.next = new ListNode(copytemp.next);
				temp = temp.next;
				copytemp = copytemp.next;
			}
		}
	}
	
	@Override
	public boolean isEmpty() {
		return (this.front == null);
	}

	@Override
	public int size() {
		int size = 0;
		ListNode temp = new ListNode(1);
		temp.next = front;
		while(temp.next != null)
		{
			size++;
			temp.next = temp.next.next;
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
			return new ReturnObjectImpl(retmsg, null);
		ListNode temp = gotoIndex(index);
		return new ReturnObjectImpl(retmsg, temp.data);
	}

	/**
	 * Function to traverse the LinkedList until it gets to the given index. Returns null if List is empty
	 * @param idx the desired index
	 * @return the ListNode requested
	 */
	private ListNode gotoIndex(int idx)
	{
		if(this.size()<idx)
			return null;
		if(idx<0)
			return null;
		int currentidx = 0;
		ListNode toret = front;
		while(idx != currentidx)
		{
			toret = toret.next;
			currentidx++;
		}
		return toret;
	}
	
	@Override
	public ReturnObject remove(int index) {
		ErrorMessage retmsg = FindError(index, false, null);
		if(retmsg != ErrorMessage.NO_ERROR)
			return new ReturnObjectImpl(retmsg, null);
		ListNode before = gotoIndex(index - 1);
		ListNode toret = gotoIndex(index);
		ListNode after = gotoIndex(index+1);
		if(before == null)//If removing the top element of the list.
		{
			front = after;
		}
		else if(after == null)//Removing the last element of the list.
		{
			before.next = null;
		}
		else//Normal scenario, bridging the link over the Node to be removed.
		{
			before.next = after;
		}
		toret.next = null;//To prevent functions that use this changing the list.
		return new ReturnObjectImpl(retmsg, toret.data);
	}

	@Override
	public ReturnObject add(int index, Object item) {
		ErrorMessage retmsg = FindError(index, true, item);
		if(retmsg != ErrorMessage.NO_ERROR)
			return new ReturnObjectImpl(retmsg, item);
		ListNode before = gotoIndex(index-1);
		ListNode toadd = new ListNode(item);
		toadd.next = before.next;
		before.next = toadd;
		return new ReturnObjectImpl(retmsg, null);
	}

	@Override
	public ReturnObject add(Object item) {
		ErrorMessage retmsg = FindError(this.size(), true, item, true);
		if(retmsg != ErrorMessage.NO_ERROR)
			return new ReturnObjectImpl(retmsg, null);
		ListNode last = gotoIndex(this.size()-1);
		ListNode toadd = new ListNode(item);
		if(last == null)
			this.front = toadd;
		else{
			last.next = toadd;
		}
		return new ReturnObjectImpl(retmsg, null);
	}

	/**
	 * Node class for the LinkedList. Contains the data and the 'link' to the next Node.
	 * @author lewispalmer
	 *
	 */
	public class ListNode{
		private Object data;
		private ListNode next;
		
		/**
		 * Constructor - creates a new ListNode with given data and next pointing to null.
		 * @param data
		 */
		public ListNode(Object data)
		{
			this.data = data;
			this.next = null;
		}
		
		/**
		 * Copy Constructor - hopefully satisfies for Functional list. Just creates a new instance of the class with same fields.
		 * @param tocopy ListNode to be copied
		 */
		public ListNode(ListNode tocopy)
		{
			this.data = tocopy.data;
			this.next = null;
		}
	}
}
