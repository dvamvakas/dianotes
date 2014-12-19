package cz.raptors.dianotes.service.exception;

/**
 * Created by vamvda1 on 27.5.14.
 */
public class FailToHashPasswordException extends Exception {

    public FailToHashPasswordException() {
        super();
    }

    public FailToHashPasswordException(String message) {
        super(message);
    }
}
