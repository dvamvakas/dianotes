package cz.raptors.dianotes.utils;

import cz.raptors.dianotes.service.exception.FailToHashPasswordException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vamvda1 on 27.5.14.
 */
@Component
public class PasswordUtils {

    Log LOGGER = LogFactory.getLog(PasswordUtils.class);

    public String hashPassword(String password, String salt) throws FailToHashPasswordException, UnsupportedEncodingException {
        String passAndSalt = password+salt;
        MessageDigest md = null;
        byte[] input = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.reset();
            input = md.digest(passAndSalt.getBytes("UTF-8"));
            md.update(passAndSalt.getBytes());
            input = md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(),e);
        }    
        
        return new String(input);
    }



}
