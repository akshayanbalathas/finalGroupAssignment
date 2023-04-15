package exception;

public class NotFoundExceptionG extends Exception{
	
	private String message;

	public NotFoundExceptionG(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
