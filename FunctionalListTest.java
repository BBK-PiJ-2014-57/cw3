import static org.junit.Assert.assertThat;


import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
/**
 * JUnit Tests class to test Functional lists by running several different scenarios as
 * parameters and checking the output of each added FunctionalList function.
 * Utilises hamcrest Matchers
 * @author lewispalmer
 *
 */
@RunWith(Parameterized.class)
public class FunctionalListTest {
	private ReturnObject expectedhead;
	private FunctionalList expectedrest;
	private FunctionalList inputList;
	private boolean ArrayList;
	private Object[] input;
	
	public FunctionalListTest(boolean array, ReturnObject head, FunctionalList rest, Object[] input)
	{
		this.ArrayList = array;
		this.expectedhead = head;
		this.expectedrest = rest;
		this.input = input;
	}
	
	/**
	 * Feeds in different FunctionalList scenarios. First of each parameters is whether to 
	 * test FunctionalLinkedList or FunctionalArrayList, to prevent two separate tests
	 * sharing similar code
	 * @return an Array of scenarios to test
	 */
	@Parameters
	public static Collection<Object[]> errFuncListdata() {
		return Arrays.asList(new Object[][] {
					{true, new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, null), 
					new FunctionalArrayList(), null},
					{true, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new FunctionalArrayList(new Object[]{2, 3, 4, 5}),
					new Object[] {1, 2, 3, 4, 5}},
					{true, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new FunctionalArrayList(),
					new Object[]{1}},
					{false, new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, null), 
					new FunctionalLinkedList(), null},
					{false, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new FunctionalLinkedList(new Object[]{2, 3, 4, 5}),
					new Object[] {1, 2, 3, 4, 5}},
					{false, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new FunctionalLinkedList(),
					new Object[]{1}},
		}
		);}
	
	/**
	 * Resets the lists to prevent any manipulation from previous Tests.
	 */
	private void InputToList()
	{
		if(this.ArrayList)
		{
			if(input!=null)
			{
				inputList = new FunctionalArrayList(input);
			}
			else
			{
				inputList = new FunctionalArrayList();
			}
		}
		else
		{
			if(input!=null)
			{
				inputList = new FunctionalLinkedList(input);
			}
			else
			{
				inputList = new FunctionalLinkedList();
			}
		}
	}
	
	/**
	 * Tests the function head() in Interface FunctionalList
	 */
	@Test
	public void headTest(){
		InputToList();
		assertThat(this.expectedhead, new returnObjectMatcher(inputList.head()));
	}
	
	/**
	 * Tests the function rest() in Interface FunctionalList
	 */
	@Test
	public void restTest(){
		InputToList();
		assertThat(this.expectedrest, new listMatcher(inputList.rest()));
	}
}
