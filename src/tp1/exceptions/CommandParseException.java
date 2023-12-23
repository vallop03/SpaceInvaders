package tp1.exceptions;

public class CommandParseException extends Exception{

	public CommandParseException() {
		super();
	}

	public CommandParseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommandParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandParseException(String message) {
		super(message);
	}

	public CommandParseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}//por crear comando malo

}
