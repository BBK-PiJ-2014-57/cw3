import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class SampleableListImplTest {

	private List expectedList;
	private Object[] expecteddata;
	private Object[] inputdata;
	private List arrayinputdata;
	private List linkedinputdata;
	private List functionalarrayinputdata;
	private List functionallinkedinputdata;
	private List sampleablelistinputdata;
	
	public SampleableListImplTest(Object[] expecteddata , Object[] inputdata)
	{
		this.expecteddata = expecteddata;
		this.inputdata = inputdata;
	}
	
	@Parameters
	public static Collection<Object[]> errFuncListdata() {
		return Arrays.asList(new Object[][] {
				{new Object[]{1, 3, 5, 7, 9},new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}},
				{new Object[]{1, 3, 5, 7, 9},new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9}},
				{new Object[]{1},new Object[]{1}}
		}
		);}
	
	private void InputToList()
	{
		this.expectedList = new ArrayList(expecteddata);
		this.arrayinputdata = new ArrayList(inputdata);
		this.linkedinputdata = new LinkedList(inputdata);
		this.functionalarrayinputdata = new FunctionalArrayList(inputdata);
		this.functionallinkedinputdata = new FunctionalLinkedList(inputdata);
		this.sampleablelistinputdata = new SampleableListImpl(inputdata);
	}
	
	@Test
	public void testArraySample()
	{
		InputToList();
		SampleableList tocheck = new SampleableListImpl(this.arrayinputdata);
		assertThat(tocheck.sample(), new listMatcher(this.expectedList));
	}
	
	@Test
	public void testLinkedSample()
	{
		InputToList();
		SampleableList tocheck = new SampleableListImpl(this.linkedinputdata);
		assertThat(tocheck.sample(), new listMatcher(this.expectedList));
	}
	
	@Test
	public void testFunctionalArraySample()
	{
		InputToList();
		SampleableList tocheck = new SampleableListImpl(this.functionalarrayinputdata);
		assertThat(tocheck.sample(), new listMatcher(this.expectedList));
	}
	
	@Test
	public void testFunctionalLinkedSample()
	{
		InputToList();
		SampleableList tocheck = new SampleableListImpl(this.functionallinkedinputdata);
		assertThat(tocheck.sample(), new listMatcher(this.expectedList));
	}
	
	@Test
	public void testSampleableListSample()
	{
		InputToList();
		SampleableList tocheck = new SampleableListImpl(this.sampleablelistinputdata);
		assertThat(tocheck.sample(), new listMatcher(this.expectedList));
	}
	
	@Test
	public void testEmptyListSampl()
	{
		SampleableList tocheck = new SampleableListImpl();
		assertThat(tocheck.sample(), new listMatcher(new ArrayList()));
	}
}
