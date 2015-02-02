/**
 * Class to be used across ArrayList and LinkedList to prevent code repetition.
 * Helps those classes find the relevant error.
 * @author lewispalmer
 *
 */
public final class FindErrorUtility {
	/**
	 * Function finds the appropriate Error message based on the operation and ArrayList
	 * @param index the index that is trying to be accessed or added
	 * @param adding whether the operation is adding to the list 
	 * @param obj the Object to be added to the ArrayList
	 * @param addtoend whether we are trying to add to the end of the 
	 * @return the appropriate Error Message
	 */
	public static ErrorMessage FindError(int index, boolean adding, Object obj, boolean addtoend, List ls)
	{
		if(ls.isEmpty() && !adding)
		{
			return ErrorMessage.EMPTY_STRUCTURE;
		}
		if(index < 0 || (index >=ls.size() && !addtoend))
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
	public static ErrorMessage FindError(int index, boolean adding, Object obj, List ls)
	{
		return FindError(index, adding, obj, false, ls);
	}
}
