import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
/**
 * hamcrest Matcher to test whether two ReturnObjects are the same.
 * @author lewispalmer
 *
 */
public class returnObjectMatcher extends TypeSafeMatcher<ReturnObject>{
	private final ReturnObject expected;
	
	public returnObjectMatcher(ReturnObject expected){
		this.expected = expected;
	}
		
	@Override
	public boolean matchesSafely(ReturnObject actual){
		if(actual == expected) return true;
		if(actual.hasError()!=expected.hasError()) return false;
		if(actual.getError()!=expected.getError()) return false;
		if(actual.getReturnValue() == null && expected.getReturnValue() == null) return true;
		if(!actual.getReturnValue().equals(expected.getReturnValue())) return false;
		return true;
	}
		
	@Override
	public void describeTo(Description descr)
	{
		descr.appendText("Not the same Return Object");
	}		
}