package Utils;

/**
 * Exception produced by the application before situations that should not occur but are controlled.
 * and therefore, the application can recover (data validation, prerequisites that are not met, etc).
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {
	public ApplicationException(Throwable e) {
		super(e);
	}
	public ApplicationException(String s) {
		super(s);
	}
}
