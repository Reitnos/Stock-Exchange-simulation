
public class Order {
	
	private String operationName;
	private String stockName;
	private int amount;
	private int priceLevel;
	
	public Order(String operationName, String stockName, int amount, int priceLevel) {
		// TODO Auto-generated constructor stub
		this.operationName = operationName;
		this.stockName = stockName;
		this.amount = amount;
		this.priceLevel = priceLevel;
		
	
		
	}
	int getPriceLevel() throws StockPriceNotBetweenBoundariesException {
		
		
		if(priceLevel < 1 || priceLevel > 1000) {
			throw new StockPriceNotBetweenBoundariesException("Given order's price level is not within boundaries of 1$ - 1000$");
		}
		return priceLevel;
	}
	String getOperationName() {
		return operationName;
	}
	int getAmount() {
		return amount;
	}

}
