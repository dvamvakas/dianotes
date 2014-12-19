package cz.raptors.dianotes.dao.exception;

/**
 * Created by vamvda1 on 1.4.14.
 */
public class AddressNotFoundException extends Exception {


    public AddressNotFoundException(){
        super();
    }

    public AddressNotFoundException(String message){
        super(message);
    }
}
