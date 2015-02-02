import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
/**
 * JUnit Tests class to test Lists by running several different scenarios as
 * parameters and checking the output of each defined List function.
 * Utilises hamcrest Matchers
 * @author lewispalmer
 *
 */
@RunWith(Parameterized.class)
public class ListTest {
	
	private boolean expectedEmptiness;
	private int expectedsize;
	private ReturnObject expectedRemoveReturnObject;
	private ReturnObject expectedAddReturnObject;
	private ReturnObject expectedAddEndReturnObject;
	private Object expectedOutput;
	private List expectedAddList;
	private List expectedAddEndList;
	private List expectedRemoveList;
	private Object[] inputList;
	private List inputAsList;
	private ErrorMessage expectedbadidxerr;
	private ErrorMessage expectedaddbadidxerr;
	private ErrorMessage insertnullerr;
	private ErrorMessage insertendnullerr;
	private boolean isArrayList;
	private List copyList;
	
	public ListTest(boolean isArray, boolean isempty, int size, ReturnObject remretobj, 
			ReturnObject addretobj, ReturnObject addendretobj, List addlist, List addendlist, 
			List removelist, Object[] input, Object retObject, ErrorMessage badidx,
			ErrorMessage insertnull, ErrorMessage insertendnull, ErrorMessage addbadidx)
	{
		this.isArrayList = isArray;
		this.expectedEmptiness = isempty;
		this.expectedsize = size;
		this.expectedRemoveReturnObject = remretobj;
		this.expectedAddReturnObject = addretobj;
		this.expectedAddEndReturnObject = addendretobj;
		this.expectedAddList = addlist;
		this.expectedAddEndList = addendlist;
		this.expectedRemoveList = removelist;
		this.inputList = input;
		this.expectedOutput = retObject;
		this.expectedbadidxerr = badidx;
		this.insertnullerr = insertnull;
		this.insertendnullerr = insertendnull;
		this.expectedaddbadidxerr = addbadidx;
	}

	/**
	 * Feeds in different List scenarios. First of each parameters is whether to 
	 * test LinkedList or ArrayList, to prevent two separate tests
	 * sharing similar code
	 * @return an Array of scenarios to test
	 */
	@Parameters
	public static Collection<Object[]> errArrayListdata() {
		return Arrays.asList(new Object[][] {
				{true, false, 5, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1), 
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ArrayList(new Object[] {1, 2, 1, 3, 4, 5}),
					new ArrayList(new Object[] {1, 2, 3, 4, 5, 1}),
					new ArrayList(new Object[] {2, 3, 4, 5}),
					new Object[] {1, 2, 3, 4, 5},
					new Integer(1), ErrorMessage.INDEX_OUT_OF_BOUNDS,
					ErrorMessage.INVALID_ARGUMENT, ErrorMessage.INVALID_ARGUMENT,
					ErrorMessage.INDEX_OUT_OF_BOUNDS
				},
				{true, true, 0, new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, null),
					new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ArrayList(),
					new ArrayList(new Object[]{1}),
					new ArrayList(),
					null,
					null, ErrorMessage.EMPTY_STRUCTURE,
					ErrorMessage.INDEX_OUT_OF_BOUNDS,ErrorMessage.INVALID_ARGUMENT,
					ErrorMessage.INDEX_OUT_OF_BOUNDS
				},
				{true, false, 3, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ArrayList(new Object[]{1, 2, 1, 3}),
					new ArrayList(new Object[]{1, 2, 3, 1}),
					new ArrayList(new Object[]{2, 3}),
					new Object[] {1, 2, 3, null, null, null},
					new Integer(1), ErrorMessage.INDEX_OUT_OF_BOUNDS,
					ErrorMessage.INVALID_ARGUMENT,ErrorMessage.INVALID_ARGUMENT,
					ErrorMessage.INDEX_OUT_OF_BOUNDS
				},
				{false, false, 5, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1), 
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new LinkedList(new Object[] {1, 2, 1, 3, 4, 5}),
					new LinkedList(new Object[] {1, 2, 3, 4, 5, 1}),
					new LinkedList(new Object[] {2, 3, 4, 5}),
					new Object[] {1, 2, 3, 4, 5},
					new Integer(1), ErrorMessage.INDEX_OUT_OF_BOUNDS,
					ErrorMessage.INVALID_ARGUMENT,ErrorMessage.INVALID_ARGUMENT,
					ErrorMessage.INDEX_OUT_OF_BOUNDS
				},
				{false, true, 0, new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, null),
					new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new LinkedList(),
					new LinkedList(new Object[]{1}),
					new LinkedList(),
					null,
					null, ErrorMessage.EMPTY_STRUCTURE,
					ErrorMessage.INDEX_OUT_OF_BOUNDS,ErrorMessage.INVALID_ARGUMENT,
					ErrorMessage.INDEX_OUT_OF_BOUNDS
				},
				{false, false, 3, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new LinkedList(new Object[]{1, 2, 1, 3}),
					new LinkedList(new Object[]{1, 2, 3, 1}),
					new LinkedList(new Object[]{2, 3}),
					new Object[] {1, 2, 3, null, null, null},
					new Integer(1), ErrorMessage.INDEX_OUT_OF_BOUNDS,
					ErrorMessage.INVALID_ARGUMENT,ErrorMessage.INVALID_ARGUMENT,
					ErrorMessage.INDEX_OUT_OF_BOUNDS
				}
		});
	}
	
	/**
	 * Resets the lists to prevent any manipulation from previous Tests.
	 */
	private void InputToList()
	{
		if(this.isArrayList)
		{
			if(inputList!=null)
			{
				inputAsList = new ArrayList(inputList);
			}
			else
			{
				inputAsList = new ArrayList();
			}
		}
		else
		{
			if(inputList!=null)
			{
				inputAsList = new LinkedList(inputList);
			}
			else
			{
				inputAsList = new LinkedList();
			}
		}
	}
	
	/**
	 * Creates a copy of the input List
	 */
	private void CopyInput()
	{
		if(this.isArrayList)
			this.copyList =  new ArrayList((ArrayList)inputAsList);
		else
			this.copyList = new LinkedList((LinkedList)inputAsList);
	}
	
	/**
	 * Tests the function defined in the List interface isEmpty()
	 */
	@Test
	public void isEmptyTest() {
		InputToList();
		assertEquals(expectedEmptiness, inputAsList.isEmpty());
	}
	
	/**
	 * Tests the function defined in the List interface size()
	 */
	@Test
	public void sizeTest(){
		InputToList();
		assertEquals(expectedsize, inputAsList.size());
	}
	
	/**
	 * Tests the function defined in the List interface get()
	 */
	@Test
	public void getTest(){
		InputToList();
		assertEquals(expectedOutput, inputAsList.get(0).getReturnValue());
	}
	
	/**
	 * Tests the ErrorMessage returned from the function defined in the List interface remove()
	 */
	@Test
	public void removeTestError(){
		InputToList();
		assertEquals(expectedRemoveReturnObject.getError(), inputAsList.remove(0).getError());
	}
	
	/**
	 * Tests the ReturnObject returned from the function defined in the List interface remove()
	 */
	@Test
	public void removeTestObject(){
		InputToList();
		assertEquals(expectedRemoveReturnObject.getReturnValue(), inputAsList.remove(0).getReturnValue());
	}
	
	/**
	 * Tests the changed List after the function defined in the List interface remove()
	 */
	@Test
	public void removeTestList(){
		InputToList();
		inputAsList.remove(0);
		assertThat(inputAsList, new listMatcher(expectedRemoveList));
	}

	/**
	 * Tests the ErrorMessage returned from the function defined in the List interface add(int, Object)
	 */
	@Test
	public void addTestError(){
		InputToList();
		assertEquals(expectedAddReturnObject.getError(), inputAsList.add(2, 1).getError());
	}
	
	/**
	 * Tests the returned ReturnObject from the function defined in the List interface add(int, Object)
	 */
	@Test
	public void addTestReturn(){
		InputToList();
		assertEquals(expectedAddReturnObject.getReturnValue(), inputAsList.add(2, 1).getReturnValue());
	}
	
	/**
	 * Tests the changed List after the function defined in the List interface add(int, Object)
	 */
	@Test
	public void addTestList(){
		InputToList();
		inputAsList.add(2, 1);
		assertThat(inputAsList, new listMatcher(expectedAddList));
	}
	
	/**
	 * Tests the ErrorMessage returned from the function defined in the List interface add()
	 */
	@Test
	public void addEndTestError(){
		InputToList();
		assertEquals(expectedAddEndReturnObject.getError(), inputAsList.add(1).getError());
	}
	
	/**
	 * Tests the ReturnObject returned from the function defined in the List interface add()
	 */
	@Test
	public void addEndTestReturn(){
		InputToList();
		assertEquals(expectedAddEndReturnObject.getReturnValue(), inputAsList.add(1).getReturnValue());
	}
	
	/**
	 * Tests the changed List after the function defined in the List interface add()
	 */
	@Test
	public void addEndTestList(){
		InputToList();
		inputAsList.add(1);
		assertThat(inputAsList, new listMatcher(expectedAddEndList));
	}
	
	/**
	 * Checked the ErrorMessage returned after trying to remove a negative index.
	 */
	@Test
	public void removenegidxTest(){
		InputToList();
		assertEquals(this.expectedbadidxerr, inputAsList.remove(-1).getError());
	}
	
	/**
	 * Checked the ErrorMessage returned after trying to remove an index larger than the list.
	 */
	@Test
	public void removebiggeridxTest(){
		InputToList();
		assertEquals(this.expectedbadidxerr, inputAsList.remove(200).getError());
	}
	
	/**
	 * Checked the ErrorMessage returned after trying to add at a negative index.
	 */
	@Test
	public void addnegidxTest(){
		InputToList();
		assertEquals(this.expectedaddbadidxerr, inputAsList.add(-1, 1).getError());
	}
	
	/**
	 * Checked the ErrorMessage returned after trying to add at an index larger than the list.
	 */
	@Test
	public void addbigidxTest(){
		InputToList();
		assertEquals(this.expectedaddbadidxerr, inputAsList.add(20, 1).getError());
	}
	/**
	 * Checked the ErrorMessage returned after trying add a null Object.
	 */
	@Test
	public void addnullTest(){
		InputToList();
		assertEquals(this.insertnullerr, inputAsList.add(0,null).getError());
	}
	
	/**
	 * Checked the ErrorMessage returned after trying add a null Object.
	 */
	@Test
	public void addendnullTest(){
		InputToList();
		assertEquals(this.insertendnullerr, inputAsList.add(null).getError());
	}
	
	/**
	 * Testing the copy Constructor, that it is a copied List, not the same List.
	 */
	@Test
	public void copyConstructorTest(){
		InputToList();
		CopyInput();
		assertThat(this.copyList, new listCopyMatcher(inputAsList));
	}
}
