package BaseProject;

/**
 * Exception produced by the application in case of uncontrolled situations.
 * (exceptions when accessing the database or when using methods that declare throwable exceptions, etc.)
 */
@SuppressWarnings("serial")
public class UnexpectedException extends RuntimeException {
	public UnexpectedException(Throwable e) {
		super(e);
	}
	public UnexpectedException(String s) {
		super(s);
	}
}
