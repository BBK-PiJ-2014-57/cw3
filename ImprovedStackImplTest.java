import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
/**
 * JUnit test class to tests the functionality of the ImproveStackImpl.
 * @author lewispalmer
 *
 */
@RunWith(Parameterized.class)
public class ImprovedStackImplTest {

	private ImprovedStack expectedreversed;
	private ImprovedStack expectedremoved;
	private Object toremove;
	private Object[] inputData;
	private ImprovedStack inputAsList;
	public ImprovedStackImplTest(ImprovedStack reversed, ImprovedStack removed, Object toremove,
			Object[] inputData)
	{
		this.expectedreversed = reversed;
		this.expectedremoved = removed;
		this.toremove = toremove;
		this.inputData = inputData;
	}
	/**
	 * Feeds in different ImprovedStack scenarios. First of each parameters is whether to 
	 * @return an Array of scenarios to test
	 */
	@Parameters
	public static Collection<Object[]> errFuncListdata() {
		return Arrays.asList(new Object[][] {
				{new ImprovedStackImpl(new ArrayList(new Object[]{5, 4, 3, 2, 1})),
					new ImprovedStackImpl(new ArrayList(new Object[]{1, 2, 4, 5})),
					3,
					new Object[]{1, 2, 3, 4, 5}
				},
				{new ImprovedStackImpl(new ArrayList(new Object[]{1})),
					new ImprovedStackImpl(new ArrayList(new Object[]{1})),
					2,
					new Object[]{1}
				},
				{new ImprovedStackImpl(new ArrayList()),
					new ImprovedStackImpl(new ArrayList()),
					1,
					new Object[]{}
				},
				{new ImprovedStackImpl(new ArrayList(new Object[]{1, 1, 2, 3, 4, 5})),
					new ImprovedStackImpl(new ArrayList(new Object[]{5, 4, 3, 2})),
					1,
					new Object[]{5, 4, 3, 2, 1, 1}
				},
				{new ImprovedStackImpl(new ArrayList(new Object[]{1, 1, 1, 1})),
					new ImprovedStackImpl(new ArrayList()),
					1,
					new Object[]{1, 1, 1, 1}
				}
		});
	}
	
	/**
	 * Resets the lists to prevent any manipulation from previous Tests.
	 */
	private void InputList()
	{
		this.inputAsList = new ImprovedStackImpl(new ArrayList(inputData));
	}
	
	/**
	 * Tests the reverse() function from ImprovedStack interface
	 */
	@Test
	public void testreverse()
	{
		InputList();
		ImprovedStack tocheck = inputAsList.reverse();
		assertThat(tocheck, new stackMatcher(this.expectedreversed));
	}
	
	/**
	 * Tests the remove() function from ImprovedStack interface
	 */
	@Test
	public void testremove()
	{
		InputList();
		inputAsList.remove(this.toremove);
		assertThat(inputAsList, new stackMatcher(this.expectedremoved));
	}
}
