import java.util.List;

public class StockPriceNotBetweenBoundariesException extends Exception {

	public StockPriceNotBetweenBoundariesException() {
		
		super("No error message specified, an error with stock price occured");
		
		
	}
	public StockPriceNotBetweenBoundariesException(String msg) {
		super(msg);
		
		
	}
	
}
