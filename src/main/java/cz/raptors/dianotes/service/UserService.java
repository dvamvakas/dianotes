package cz.raptors.dianotes.service;


import java.io.UnsupportedEncodingException;

import cz.raptors.dianotes.models.RegisterUserModel;
import cz.raptors.dianotes.pages.logged.model.LoggedUserModel;
import cz.raptors.dianotes.service.exception.FailToHashPasswordException;
import cz.raptors.dianotes.service.exception.FailedToLoginUserException;
import cz.raptors.dianotes.service.exception.UnknownEmailException;


/**
 * Created by vamvda1 on 1.4.14.
 */

public interface UserService {
	
	/**
	 * This method should be user for saving new user to the system
	 * @param registerUserModel
	 * @return
	 * @throws FailToHashPasswordException
	 */
    public RegisterUserModel registerUser(RegisterUserModel registerUserModel) throws FailToHashPasswordException, UnsupportedEncodingException;
    
    /**
     * This method should 
     * @param email
     * @param password
     * @return
     * @throws FailedToLoginUserException
     * @throws UnknownEmailException
     * @throws FailToHashPasswordException 
     */
    public LoggedUserModel loginUser(String email, String password) throws FailedToLoginUserException, UnknownEmailException, FailToHashPasswordException, UnsupportedEncodingException;


}
