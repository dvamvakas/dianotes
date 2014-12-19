package cz.raptors.dianotes.pages.registration.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by vamvda1 on 25.4.14.
 */
public class StrongPasswordValidator implements IValidator<String> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7400965419114897208L;

	private final String PASSWORD_PATTERN
            = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    private final int MINIMUM_PASSWORD_LENGTH = 8;

    private final Pattern pattern;

    public StrongPasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public void validate(IValidatable<String> validatable) {
        List<String> errors = new ArrayList<String>();
        final String password = validatable.getValue();

        if(pattern.matcher(password).matches() == false){
            errors.add("registrationForm.passwordNotStrong");
        }
        if(password.length() <= MINIMUM_PASSWORD_LENGTH){
            errors.add("registrationForm.passwordNotLongEnough");
        }
        if(!errors.isEmpty()){
            error(validatable,errors);
        }

    }
    private void error(IValidatable<String> validatable, List<String> errors) {
        ValidationError error = new ValidationError();
        error.setKeys(errors);
        validatable.error(error);
    }
}
