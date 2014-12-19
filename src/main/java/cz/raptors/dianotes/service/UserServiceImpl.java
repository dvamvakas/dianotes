package cz.raptors.dianotes.service;

import cz.raptors.dianotes.dao.UserDao;
import cz.raptors.dianotes.entities.Address;
import cz.raptors.dianotes.entities.AssignedRoles;
import cz.raptors.dianotes.entities.HealthCard;
import cz.raptors.dianotes.entities.User;
import cz.raptors.dianotes.entities.UserRole;
import cz.raptors.dianotes.entities.enums.UserRoleType;
import cz.raptors.dianotes.models.RegisterUserModel;
import cz.raptors.dianotes.pages.logged.model.LoggedUserModel;
import cz.raptors.dianotes.service.exception.FailToHashPasswordException;
import cz.raptors.dianotes.service.exception.FailedToLoginUserException;
import cz.raptors.dianotes.service.exception.UnknownEmailException;
import cz.raptors.dianotes.utils.PasswordUtils;

import org.apache.log4j.pattern.LogEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamvda1 on 27.5.14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Inject
    private UserDao userDao;

    @Inject
    private PasswordUtils passwordUtils;
    

    @Override
    @Transactional
    public RegisterUserModel registerUser(RegisterUserModel registerUserModel) throws FailToHashPasswordException, UnsupportedEncodingException {
        User user = new User();
        user.setFirstName(registerUserModel.getFirstName());
        user.setAddress(new Address());
        List<AssignedRoles> assignedRoles = new ArrayList<AssignedRoles>();
        UserRole userRole = new UserRole();
        userRole.setUserRoleType(UserRoleType.PATIENT);
        assignedRoles.add(new AssignedRoles.Builder().userRole(userRole).build());
        user.setUserRoleList(assignedRoles);
        user.setLastName(registerUserModel.getLastName());
        user.setEmail(registerUserModel.getEmail());
        user.setPassword(passwordUtils.hashPassword(registerUserModel.getPassword(),registerUserModel.getEmail()));
        user.setSex("MUZ");
        user.setHealthCard(new HealthCard());
        user = userDao.registerNewUser(user);
        return registerUserModel;
    }

	@Override
	public LoggedUserModel loginUser(String email, String password)
			throws FailedToLoginUserException, FailToHashPasswordException, UnsupportedEncodingException {
		LoggedUserModel loggedUserModel = new LoggedUserModel();
		password = passwordUtils.hashPassword(password, email);
		List<User> users = userDao.getUserByEmailAndPassword(email, password);
		if(users.isEmpty()){
			throw new FailedToLoginUserException("Nothink was returned for email: " +email);
		}else if(users.size() > 1){
			throw new FailedToLoginUserException("Multiple users for one email returned: " +email);			
		}
		else if(!users.get(0).getPassword().equals(password)){
			throw new FailedToLoginUserException("Failed to login user: " + users.get(0).getEmail());
		}else{
			
			loggedUserModel.setEmail(users.get(0).getEmail());
			loggedUserModel.setFirstName(users.get(0).getFirstName());
			loggedUserModel.setLastName(users.get(0).getLastName());
			loggedUserModel.setSex(users.get(0).getSex());
			loggedUserModel.setPassword(users.get(0).getPassword());
		}
		return loggedUserModel;
	}
}
