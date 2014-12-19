package cz.raptors.dianotes.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import cz.raptors.dianotes.dao.exception.UserNotFoundException;
import cz.raptors.dianotes.entities.User;

import org.springframework.stereotype.Repository;

/**
 * Created by vamvda1 on 1.4.14.
 */
@Repository
public class UserDaoImpl extends GenericDaoJpa<User, UserNotFoundException> implements UserDao{


    @Override
    public User registerNewUser(User user) {
        user = merge(user);
        return user;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected UserNotFoundException getException(String message) {
        return new UserNotFoundException(message);
    }

	@Override
	public List<User> getUserByEmailAndPassword(String email, String password) {
		TypedQuery<User> query = getNamedQuery(User.GET_USER_BY_EMAIL_AND_PASSWORD);
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.getResultList();
	}

}
