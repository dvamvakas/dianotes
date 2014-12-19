package cz.raptors.dianotes.models;


import org.apache.wicket.util.io.IClusterable;

/**
 * Created by vamvda1 on 24.4.14.
 */
public class RegisterUserModel implements IClusterable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7975865277677111355L;

	private String password;

    private String password2;

    private String firstName;

    private String lastName;

    private String sex;

    private String email;

    public RegisterUserModel() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
