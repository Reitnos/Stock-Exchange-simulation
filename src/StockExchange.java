import java.util.TimerTask;
import java.util.Vector;

public class StockExchange extends TimerTask {
	Vector<Stock> selectedStocks;
	
	public StockExchange(Vector<Stock> selectedStocks) {
		this.selectedStocks = selectedStocks;
	}
	
	@Override
	public void run() {
		marketSimulate(selectedStocks);
	}
	
	public void marketSimulate(Vector<Stock> selectedStocks) {
		for(int i = 0; i < selectedStocks.size(); i++) {
			selectedStocks.get(i).printInfo();
			boolean isThereUserOperation =  selectedStocks.get(i).performReadyOrders(); 	// check if there is appropriate order needs to be executed
			if(!isThereUserOperation) {
				
				selectedStocks.get(i).changePriceOverTime();
			}
			
			
		
			
		}
		System.out.println("-------");
		
	}
}
