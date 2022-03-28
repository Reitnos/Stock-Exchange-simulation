
public class UnidentifiedOperationTypeException extends Exception{

	public UnidentifiedOperationTypeException() {
		super("default message for unidentified operation type exception");
	}
	
	public UnidentifiedOperationTypeException(String msg) {
		super(msg);
		
		System.out.println("Provide an acceptable operation type: buy - sell");
	}
}
