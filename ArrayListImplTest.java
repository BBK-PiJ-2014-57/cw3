import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
 


import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(Parameterized.class)
public class ArrayListImplTest {
	
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
	
	public ArrayListImplTest(boolean isempty, int size, ReturnObject remretobj, 
			ReturnObject addretobj, ReturnObject addendretobj, List addlist, List addendlist, 
			List removelist, Object[] input, Object retObject)
	{
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
	}

	@Parameters
	public static Collection<Object[]> errArrayListdata() {
		return Arrays.asList(new Object[][] {
				{false, 5, new ReturnObjectImpl(ErrorMessage.NO_ERROR, 1), 
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ArrayListImpl(new Object[] {1, 2, 1, 3, 4, 5}),
					new ArrayListImpl(new Object[] {1, 2, 3, 4, 5, 1}),
					new ArrayListImpl(new Object[] {2, 3, 4, 5}),
					new Object[] {1, 2, 3, 4, 5},
					new Integer(1)
				},
				{true, 0, new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, null),
					new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS, null),
					new ReturnObjectImpl(ErrorMessage.NO_ERROR, null),
					new ArrayListImpl(),
					new ArrayListImpl(new Object[]{1}),
					new ArrayListImpl(),
					null,
					null
				}
				
		});
	}
	
	private void InputToList()
	{
		if(inputList!=null)
		{
			inputAsList = new ArrayListImpl(inputList);
		}
		else
		{
			inputAsList = new ArrayListImpl();
		}
	}
	
	@Test
	public void isEmptyTest() {
		InputToList();
		assertEquals(expectedEmptiness, inputAsList.isEmpty());
	}
	
	@Test
	public void sizeTest(){
		InputToList();
		assertEquals(expectedsize, inputAsList.size());
	}
	
	@Test
	public void getTest(){
		InputToList();
		assertEquals(expectedOutput, inputAsList.get(0).getReturnValue());
	}
	
	@Test
	public void removeTestError(){
		InputToList();
		assertEquals(expectedRemoveReturnObject.getError(), inputAsList.remove(0).getError());
	}
	
	@Test
	public void removeTestObject(){
		InputToList();
		assertEquals(expectedRemoveReturnObject.getReturnValue(), inputAsList.remove(0).getReturnValue());
	}
	
	@Test
	public void removeTestList(){
		InputToList();
		inputAsList.remove(0);
		assertEquals(expectedRemoveList, inputAsList);
	}

	@Test
	public void addTestError(){
		InputToList();
		assertEquals(expectedAddReturnObject.getError(), inputAsList.add(2, 1).getError());
	}
	
	@Test
	public void addTestReturn(){
		InputToList();
		assertEquals(expectedAddReturnObject.getReturnValue(), inputAsList.add(2, 1).getReturnValue());
	}
	
	@Test
	public void addTestList(){
		InputToList();
		inputAsList.add(2, 1);
		assertEquals(expectedAddList, inputAsList);
	}
}
