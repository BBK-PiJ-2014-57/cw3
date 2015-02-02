import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class listCopyMatcher extends TypeSafeMatcher<List>{
	private final List expected;
	
		
	public listCopyMatcher(List inputAsList) {
		this.expected = inputAsList;
	}

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
