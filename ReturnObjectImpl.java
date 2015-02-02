
/**
 * A class which implements the class ReturnObject.java.
 * @author lewispalmer
 *
 */
public class ReturnObjectImpl implements ReturnObject {

	private ErrorMessage errMsg;
	private boolean err;
	private Object retObject;
	
	
	/**
	 * Constructor - taking in all three fields.
	 * 
	 * @param error whether there is an error
	 * @param msg enum'd error message
	 * @param retObj object returned
	 */
	public ReturnObjectImpl(boolean error, ErrorMessage msg, Object retObj)
	{
		this.err = error;
		this.errMsg = msg;
		this.retObject = retObj;
	}
	
	/**
	 * Constructor - taking in two fields and working out the err field.
	 * @param msg Error Message associated with the Object/ Operation
	 * @param retObj Object to be returned.
	 */
	public ReturnObjectImpl(ErrorMessage msg, Object retObj)
	{
		this.err = (!msg.equals(ErrorMessage.NO_ERROR));
		this.errMsg = msg;
		this.retObject = retObj;
	}
	
	/**
	 * Copy Constructor - hopefully satisfies for Functional list. Just creates a new instance of the class with same fields.
	 * @param tocopy ReturnObject to be copied
	 */
	public ReturnObjectImpl(ReturnObjectImpl tocopy)
	{
		this.err = tocopy.err;
		this.errMsg = tocopy.errMsg;
		this.retObject = tocopy.retObject;
	}
	
	@Override
	public boolean hasError() {
		return this.err;
	}

	
	@Override
	public ErrorMessage getError() {
		if(!this.err)
			return ErrorMessage.NO_ERROR;
		else
			return this.errMsg;
	}

	
	@Override
	public Object getReturnValue() {
		if(this.err)
			return null;
		else
			return this.retObject;
	}
}
