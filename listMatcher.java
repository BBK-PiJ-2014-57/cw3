import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
/**
 * hamcrest Matcher testing whether two lists are the same
 * @author lewispalmer
 *
 */
public class listMatcher extends TypeSafeMatcher<List>{
	private final List expected;
	
	public listMatcher(List expected){
		this.expected = expected;
	}
	
	/**
	 * Essentially creates a .equals() method.
	 */
	@Override
	public boolean matchesSafely(List actual){
		if(actual == expected) return true;
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
