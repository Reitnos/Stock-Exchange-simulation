
public class InvalidOperationAmountException extends Exception{

	public InvalidOperationAmountException() {
		super("This is a default exception message for invalid operation amount. Check the available number of stocks again");
	}
	
	public InvalidOperationAmountException(String msg, int availableStockNumber) {
		super(msg);
		System.out.println("You can only buy less than or equal to "+ availableStockNumber);
	}

}
