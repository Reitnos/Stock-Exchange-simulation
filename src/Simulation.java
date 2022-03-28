import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;

import javax.swing.plaf.multi.MultiInternalFrameUI;

public class Simulation {

	public static void main(String[] args) {
		Vector<Stock> selectedStocks = randomlySelectAssetsFromPool();
	
		for(int i = 0; i < selectedStocks.size(); i++) {
			selectedStocks.get(i).printInfo();
			
		}
		
		System.out.println("Please place your orders on the market: ");
		
		
		Scanner scanner = new Scanner(System.in);
		String readString = scanner.nextLine();
		
	
		takeUserOrders(selectedStocks, scanner, readString);
		
		StockExchange se1 = new StockExchange(selectedStocks);
		
		Timer timer = new Timer();
		timer.schedule(se1, 0, 5000);
		
		
	}



	private static void takeUserOrders(Vector<Stock> selectedStocks, Scanner scanner, String readString) {
		while(true) {
		
		   
		    // trim the given vectors by the spaces and create an array of them.
		    String[] splitStr = readString.trim().split("\\s+");
		    List<String> entryList = Arrays.asList(splitStr);  
		    
		    if (readString.isEmpty()) {
		        
		        break;
		    }
		  
		    

		    if (scanner.hasNextLine()) {
		        readString = scanner.nextLine();
		      
		    } else {
		    	readString = null;
		    	break; 
		    }
		    
		
		    String operationName = "" ;
		    
		    String stockName = "";
		    
		    int amount = 0 ;
		    
		    int priceLevel = 0;
		    
		    try {
		    	operationName = entryList.get(0);
		    	stockName = entryList.get(1);
		    	 amount = Integer.parseInt(entryList.get(2));
		    	 priceLevel = Integer.parseInt(entryList.get(3));
		    	 
		    }
		    catch (NumberFormatException e) {
				e.getMessage();
				System.exit(1);
			}
		    
		    Order newOrder = new Order(operationName, stockName, amount, priceLevel);
		    
		    addOrdersToStocks(selectedStocks, stockName, newOrder);
		    
		 

		    
		    
		}
	}



	private static void addOrdersToStocks(Vector<Stock> selectedStocks, String stockName, Order newOrder) {
		for(Stock stock : selectedStocks) {
			
			if(stock.getName().equals(stockName)) {
				stock.addNewOrder(newOrder);
			}
   
		}
	}

	
	
	private static Vector<Stock> randomlySelectAssetsFromPool() {
		Vector<Stock> selectedStocks = new Vector<Stock>();
		Vector<Stock> poolStocks = generateStockPool(); 
		
		// this may not work check it out.
		int min = 5;
		int max = 10;
		int value = (int) Math.floor(Math.random()* (max-min+1) + min);
		
		
		while(value > 0) {
			int minIndex = 0;
			int maxIndex = poolStocks.size()-1;
			int randomIndex = (int) Math.floor(Math.random()* (maxIndex-minIndex+1) + minIndex);
			selectedStocks.add(poolStocks.get(randomIndex));
			poolStocks.remove(randomIndex);
			value--;
		}
		return selectedStocks;
	}

	private static Vector<Stock> generateStockPool() {
		
		Vector<Stock> poolStocks = new Vector<Stock>();
		
		Stock s1 = new Stock("A");
		Stock s2 = new Stock("B");
		Stock s3 = new Stock("C");
		Stock s4 = new Stock("D");
		Stock s5 = new Stock("E");
		Stock s6 = new Stock("F");
		Stock s7 = new Stock("G");
		Stock s8 = new Stock("H");
		Stock s9 = new Stock("I");
		Stock s10 = new Stock("J");
		Stock s11 = new Stock("K");
		Stock s12 = new Stock("L");
		Stock s13 = new Stock("M");
		Stock s14 = new Stock("N");
		Stock s15 = new Stock("O");
			
		poolStocks.add(s1);
		poolStocks.add(s2);
		poolStocks.add(s3);
		poolStocks.add(s4);
		poolStocks.add(s5);
		poolStocks.add(s6);
		poolStocks.add(s7);
		poolStocks.add(s8);
		poolStocks.add(s9);
		poolStocks.add(s10);
		poolStocks.add(s11);
		poolStocks.add(s12);
		poolStocks.add(s13);
		poolStocks.add(s14);
		poolStocks.add(s15);
		
		return poolStocks;
	}

}
