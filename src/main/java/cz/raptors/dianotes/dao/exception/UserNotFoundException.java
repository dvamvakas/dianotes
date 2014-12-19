package cz.raptors.dianotes.dao.exception;

import java.io.Serializable;

/**
 * Created by vamvda1 on 1.4.14.
 */
public class UserNotFoundException extends Exception {


    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
