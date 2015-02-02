import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * hamcrest Matcher to test the copy of a ReturnObject 
 * @author lewispalmer
 *
 */
public class returnObjectCopyTester extends TypeSafeMatcher<ReturnObject> {
	private final ReturnObjectImpl expected;
	
	public returnObjectCopyTester(ReturnObjectImpl expected){
		this.expected = expected;
	}
		
	/**
	 * Essentially creates a .equals() method but returns false is they have the same
	 * memory address.
	 */
	@Override
	public boolean matchesSafely(ReturnObject actual){
		if(actual == expected) return false;
		if(actual.hasError() != expected.hasError())return false;
		if(actual.getError() != expected.getError())return false;
		if(!actual.getReturnValue().equals(expected.getReturnValue())) return false;
		return true;
	}
		
	@Override
	public void describeTo(Description descr)
	{
		descr.appendText("Not the same ReturnObjectImpl");
	}		
}
