import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Stock {

	private int price; 
	private String name;
	private int numOfShares;
	
	Vector<Order> orders = new Vector<Order>();
	
	Stock(String name) {
		int minValue = 1;
		int maxValue = 1000;
		this.price = randomValueGenerate(minValue, maxValue);
		
		
		
		int minShares = 1;
		int maxshares = 10;
		
		this.numOfShares = randomValueGenerate(minShares, maxshares);
		
		this.name = name;
	}
	
	void addNewOrder(Order newOrder) {
		orders.add(newOrder);
	}
	
	 String getName() {
		return name;
	}

	private int randomValueGenerate(int min, int max) {
		int randomValue = (int) Math.floor(Math.random()* (max-min+1) + min); 
		return randomValue;
	}
	
	void printInfo() {
		System.out.println("Stock name: " + name + " - Value: " + price + " $" + "- # of shares: " + numOfShares);
	}
	
	void changeAmount(int amountOfChange) throws InvalidOperationAmountException {
		if(numOfShares + amountOfChange < 0) {
			throw new InvalidOperationAmountException("You have entered an invalid amount of stock number",numOfShares);
		}
		numOfShares += amountOfChange;
		
	}
	void changePrice(double ratio) throws StockPriceNotBetweenBoundariesException {
		int newPrice =  price + (int)(Math.floor(price * ratio));
		if(newPrice < 1) {
			newPrice = 1;
		}
		else if(newPrice > 1000) {
			newPrice = 1000;
		}
		
		if(price > 1000 || price < 1) {
			throw new StockPriceNotBetweenBoundariesException("Stock price is greater than 1000 $ or lower than 1 $");
		}
		price = newPrice;
	}
	boolean performReadyOrders() {
		// this can be made more logical by saying, if the operation is buy
		// then if the current price is already lower than wanted price just buy already.
		boolean anOperationPerformed = false;
		Order previousOrder = null;
		for(Order currentOrder : orders) {
			if(previousOrder != null) {
				orders.remove(previousOrder);
			}
			int currentOrdersPriceLevel = 0;
			try {
				currentOrdersPriceLevel =  currentOrder.getPriceLevel();
			} catch (StockPriceNotBetweenBoundariesException e) {
				e.getMessage();
				System.exit(1);
			}
			if(price == currentOrdersPriceLevel) {
				try {
					 changeDueToNewOperation(currentOrder);
				} catch (UnidentifiedOperationTypeException e) {
					e.getMessage();
					System.exit(1);
					
				}
			   
				previousOrder = currentOrder;
			//	orders.remove(order);
				anOperationPerformed = true;
			}
			
			
		}
		return anOperationPerformed;
	}
	
	void changePriceOverTime() { 
		
		int minChange = 1;
		int maxChange = 3;
		boolean decrease = false;
		
	//	System.out.println("Old price: " + price);

		int valueChange= randomValueGenerate(minChange, maxChange);
		int signOfChange = randomValueGenerate(0, 1);
		if(signOfChange == 0) {
			if(price + valueChange <= 1000) {
				
				this.price += valueChange;
			}
			else {
				this.price = 1000;
			}
		}
		else {
			if(price - valueChange >= 1) {
				
				this.price -= valueChange;
			}
			else {
				this.price = 1;
			}
		}
	//	System.out.println(" New price: " + price);
	//	System.out.println("------------------------------");
	}
	
	void changeDueToNewOperation(Order order) throws UnidentifiedOperationTypeException {
		
			double changeRatio = (double) order.getAmount() / numOfShares;
			
			if(order.getOperationName().equals("buy")) {
				
				try {
					changeAmount(-order.getAmount());
				} catch (InvalidOperationAmountException e) {
					e.getMessage();
					System.exit(1);
				}
				
				
				try {
					changePrice(changeRatio);
				} catch (StockPriceNotBetweenBoundariesException e) {
					e.getMessage();
					System.exit(1);
				}
			    
			}
			else if(order.getOperationName().equals("sell")) {
				try {
					changeAmount(order.getAmount());
				} catch (InvalidOperationAmountException e) {
					e.getMessage();
					System.exit(1);
				}
				
				try {
					changePrice(-changeRatio);
				} catch (StockPriceNotBetweenBoundariesException e) {
					e.getMessage();
					System.exit(1);
				}
			}
			else{
				throw new UnidentifiedOperationTypeException("Given operation type on stock is unidentified");
			}
			
			
	}
}
