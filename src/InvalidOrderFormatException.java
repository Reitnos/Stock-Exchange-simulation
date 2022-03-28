
public class InvalidOrderFormatException extends Exception{

	public InvalidOrderFormatException() {
		super("Default message for invalid order format exception"); 
	}
	public InvalidOrderFormatException(String msg){
		super(msg);
		
		System.out.println("You need to provide order in the following format: <Operation type> <Stock name> <Amount> <Price level>");
	}

}
