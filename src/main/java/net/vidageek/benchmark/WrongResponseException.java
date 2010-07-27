package net.vidageek.benchmark;

public class WrongResponseException extends RuntimeException {
	private static final long serialVersionUID = 4351340954698287232L;

	public WrongResponseException() {
	}

	public WrongResponseException(String message) {
		super(message);
	}
}
