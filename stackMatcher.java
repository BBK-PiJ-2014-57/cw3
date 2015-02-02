import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
/**
 * hamcrest Matcher to check whether two Stacks have the same data.
 * @author lewispalmer
 *
 */
public class stackMatcher extends TypeSafeMatcher<Stack>{
	private final Stack expected;
	
	public stackMatcher(Stack expected){
		this.expected = expected;
	}
		
	@Override
	public boolean matchesSafely(Stack actual){
		if(actual == expected) return true;
		if(actual.size()!=expected.size()) return false;
		for(int i = 0; i<actual.size(); i++)
		{
			if(actual.top().getReturnValue() != expected.top().getReturnValue())
			{
				return false;
			}
			actual.pop();
			expected.pop();
		}
		return true;
	}
		
	@Override
	public void describeTo(Description descr)
	{
		descr.appendText("Not the same Stack");
	}		
}
