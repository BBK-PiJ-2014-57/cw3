import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
/**
 * JUnit test class to test all the functions in the ReturnObject interface.
 * @author lewispalmer
 *
 */
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
	
	/**
	 * Creates an array of tests cases.
	 * @return an array of test cases.
	 */
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
	
	/**
	 * Tests the function hasError() from interface ReturnObject
	 */
	@Test
	public void hasErrorTest() {
		assertEquals(expectedError, input.hasError());
	}
	
	/**
	 * Tests the function getError() from interface ReturnObject
	 */
	@Test
	public void getErrorTest() {
		assertEquals(expectedMessage, input.getError());
	}
	
	/**
	 * Tests the function getReturnValue() from interface ReturnObject
	 */
	@Test
	public void getReturnValueTest(){
		assertEquals(expectedObject, input.getReturnValue());
	}

	/**
	 * Tests the presence of an error of a copy from the Constructor in ReturnObjectImpl
	 */
	@Test
	public void copyConstructorTestError(){
		assertEquals(new ReturnObjectImpl((ReturnObjectImpl)input).hasError(), input.hasError());
	}
	
	/**
	 * Tests the ErrorMessage of a copy from the Constructor in ReturnObjectImpl
	 */
	@Test
	public void copyConstructorTestErrorMsg(){
		assertEquals(new ReturnObjectImpl((ReturnObjectImpl)input).getError(), input.getError());
	}
	
	/**
	 * Tests the ReturnValue of a copy from the Constructor in ReturnObjectImpl
	 */
	@Test
	public void copyConstructorTestRetObj(){
		assertEquals(new ReturnObjectImpl((ReturnObjectImpl)input).getReturnValue(), input.getReturnValue());
	}
	
	/**
	 * Tests the difference of memory address of a copy from the Constructor in ReturnObjectImpl
	 */
	@Test
	public void copyConstructorDiffObj(){
		assertThat(new ReturnObjectImpl((ReturnObjectImpl)input), new returnObjectCopyTester((ReturnObjectImpl)input));
	}
}
