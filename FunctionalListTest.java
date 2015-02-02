import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
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
	
	@Test
	public void headTest(){
		InputToList();
		assertThat((ReturnObjectImpl)this.expectedhead, new returnObjectMatcher((ReturnObjectImpl)inputList.head()));
	}
	
	@Test
	public void restTest(){
		InputToList();
		assertThat(this.expectedrest, new listMatcher(inputList.rest()));
	}
}
