import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
/**
 * JUnit test class to test the functionalist of the methods defined in the interface
 * Stack
 * @author lewispalmer
 *
 */
@RunWith(Parameterized.class)
public class StackImplTest {

	private boolean expectedisempty;
	private int expectedsize;
	private List expectedafterpush;
	private ReturnObject expectedaftertop;
	private ReturnObject expectedafterpop;
	private List expectedlistafterpop;
	private Object[] input;
	private StackImpl inputAsList;
	private Object itemtopush;
	public StackImplTest(boolean empty, int size, List pushed, ReturnObject top, 
			ReturnObject popped, List poppedlist, Object[] inputdata, Object itemtopush)
	{
		this.expectedisempty = empty;
		this.expectedsize = size;
		this.expectedafterpush = pushed;
		this.expectedaftertop = top;
		this.expectedafterpop = popped;
		this.expectedlistafterpop = poppedlist;
		this.input = inputdata;
		this.itemtopush = itemtopush;
	}
	
	/**
	 * Builds out the test cases
	 * @return returns an array of test scenarios
	 */
	@Parameters
	public static Collection<Object[]> errFuncListdata() {
		return Arrays.asList(new Object[][] {
				{false, 5, new ArrayList(new Object[]{6, 1, 2, 3, 4, 5}), 
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new ArrayList(new Object[] {2, 3, 4, 5}),
					new Object[] {1, 2, 3, 4, 5},
					6
				},
				{true, 0, new ArrayList(new Object[]{6}),
					new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, null),
					new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, null),
					new ArrayList(),
					new Object[]{},
					6
				},
				{false, 1, new ArrayList(new Object[]{1}),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1),
					new ArrayList(),
					new Object[]{1},
					null
				}
		});
	}
	
	/**
	 * Re-sets the Lists to prevent changes from the other Tests
	 */
	private void InputToList()
	{
		inputAsList = new StackImpl(new ArrayList(input));
	}
	
	/**
	 * Tests the implementation of the method isEmpty() from Stack Interface
	 */
	@Test
	public void testisEmpty()
	{
		InputToList();
		assertEquals(this.expectedisempty, inputAsList.isEmpty());
	}
	
	/**
	 * Tests the implementation of the method size() from Stack Interface
	 */
	@Test
	public void testsize()
	{
		InputToList();
		assertEquals(this.expectedsize, inputAsList.size());
	}
	
	/**
	 * Tests the implementation of the method top() from Stack Interface
	 */
	@Test
	public void testtop()
	{
		InputToList();
		assertThat(this.expectedaftertop, new returnObjectMatcher(inputAsList.top()));
	}
	
	/**
	 * Tests the implementation of the method push() from Stack Interface
	 */
	@Test
	public void testpush()
	{
		InputToList();
		inputAsList.push(this.itemtopush);
		assertThat(inputAsList, new stackMatcher(new StackImpl(this.expectedafterpush)));
	}
	
	/**
	 * Tests the returned value and implementation of the method pop() from Stack Interface
	 */
	@Test
	public void testReturnObjectpop()
	{
		InputToList();
		assertThat(inputAsList.pop(), new returnObjectMatcher(this.expectedafterpop));
	}
	
	/**
	 * Tests the changed Stack from the method pop() from Stack Interface
	 */
	@Test
	public void testStackpop()
	{
		InputToList();
		inputAsList.pop();
		assertThat(inputAsList, new stackMatcher(new StackImpl(this.expectedlistafterpop)));
	}
}
