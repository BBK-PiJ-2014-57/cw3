/**
 * A class which implements the class ReturnObject.java.
 * @author lewispalmer
 *
 */
public class ReturnObjectImpl implements ReturnObject {

	private ErrorMessage errMsg;
	private boolean err;
	private Object retObject;
	
	//Constructor - taking in the three fields.
	public ReturnObjectImpl(boolean error, ErrorMessage msg, Object retObj)
	{
		this.err = error;
		this.errMsg = msg;
		this.retObject = retObj;
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
