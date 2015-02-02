import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
/**
 * hamcrest Matcher to test whether a list and it's copy have the same elements but are not
 * the same Object.
 * @author lewispalmer
 *
 */
public class listCopyMatcher extends TypeSafeMatcher<List>{
	private final List expected;
	
		
	public listCopyMatcher(List inputAsList) {
		this.expected = inputAsList;
	}

	/**
	 * Creates a .equals() method, changing the same Object check.
	 */
	@Override
	public boolean matchesSafely(List actual){
		if(actual == expected) return false;
		if(actual.size()!=expected.size()) return false;
		for(int i = 0; i<actual.size(); i++)
		{
			if(actual.get(i).getReturnValue() != expected.get(i).getReturnValue())
			{
				return false;
			}
		}
		return true;
	}
		
	@Override
	public void describeTo(Description descr)
	{
		descr.appendText("Not the same list");
	}		
}
