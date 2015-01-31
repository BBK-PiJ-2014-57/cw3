import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
 

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class ReturnObjectImplTest {

	private static Object fortest = new Integer(1);
	private boolean expectedError;
	private ErrorMessage expectedMessage;
	private Object expectedObject;
	private ReturnObject input;
	
	public ReturnObjectImplTest(boolean expError, ErrorMessage expMessage, Object expObj, ReturnObject input)
	{
		this.expectedObject = expObj;
		this.expectedMessage = expMessage;
		this.expectedError = expError;
		this.input = input;
	}
	
	@Parameters
	public static Collection<Object[]> errdata() {
		return Arrays.asList(new Object[][] {
				{true, ErrorMessage.INDEX_OUT_OF_BOUNDS, null, new ReturnObjectImpl(true, ErrorMessage.INDEX_OUT_OF_BOUNDS, fortest)},
				{false, ErrorMessage.NO_ERROR, new Integer(1), new ReturnObjectImpl(false, ErrorMessage.NO_ERROR, fortest)},
				{true, ErrorMessage.EMPTY_STRUCTURE, null, new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE, fortest)},
				{true, ErrorMessage.INDEX_OUT_OF_BOUNDS, null, new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS, fortest)},
				{true, ErrorMessage.INVALID_ARGUMENT, null, new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT, fortest)},
				{false, ErrorMessage.NO_ERROR, new Integer(1), new ReturnObjectImpl(ErrorMessage.NO_ERROR, fortest)}
				
		});
	}
	
	@Test
	public void hasErrorTest() {
		assertEquals(expectedError, input.hasError());
	}
	
	@Test
	public void getErrorTest() {
		assertEquals(expectedMessage, input.getError());
	}
	
	@Test
	public void getReturnValueTest(){
		assertEquals(expectedObject, input.getReturnValue());
	}

}
