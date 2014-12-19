package cz.raptors.dianotes.service.exception;

public class UnknownEmailException extends Exception {

	public UnknownEmailException() {
		super();
	}
	
	public UnknownEmailException(String message){
		super(message);
	}

}
