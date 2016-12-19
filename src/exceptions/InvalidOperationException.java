package exceptions;

public class InvalidOperationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String s;

	public InvalidOperationException(String s) {
		// TODO Auto-generated constructor stub
		this.s = s;
	}

}
