package tp1.exceptions;

public class CommandExecuteException extends Exception{

	public CommandExecuteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommandExecuteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CommandExecuteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CommandExecuteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CommandExecuteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}//se lanza en funcion execute de comandos porq no se puede ejecutar comando

	
}
