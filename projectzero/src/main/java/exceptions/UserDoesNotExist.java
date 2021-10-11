package exceptions;

public class UserDoesNotExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserDoesNotExist() {
		super("Username doesn't exist");
	}

}
