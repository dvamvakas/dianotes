package cz.raptors.dianotes.dao;

import java.util.List;

import cz.raptors.dianotes.dao.exception.UserNotFoundException;
import cz.raptors.dianotes.entities.User;

/**
 * Created by vamvda1 on 1.4.14.
 */
public interface UserDao extends GenericDao<User, UserNotFoundException> {

    public User registerNewUser(User user);
    
    public List<User> getUserByEmailAndPassword(String email, String password);

}
