package tp1.exceptions;

public class GameModelException extends Exception {
//5 constructores siempre

	public GameModelException() {
		super();//vacio
	}

	public GameModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GameModelException(String message, Throwable cause) {
		super(message, cause);
		//guarda mensaje de error y causa 
	}

	public GameModelException(String message) {
		super(message);
		//guarda mensaje de error
	}

	public GameModelException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
