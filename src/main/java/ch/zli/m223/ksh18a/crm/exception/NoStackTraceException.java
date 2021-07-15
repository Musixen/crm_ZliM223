package ch.zli.m223.ksh18a.crm.exception;

@SuppressWarnings("serial")
public class NoStackTraceException extends RuntimeException {
	public NoStackTraceException() {
		super("", null, true, false);
	}
}
