package cz.raptors.dianotes.pages.registration;

import java.io.UnsupportedEncodingException;

import cz.raptors.dianotes.entities.User;
import cz.raptors.dianotes.models.RegisterUserModel;
import cz.raptors.dianotes.pages.registration.validators.StrongPasswordValidator;
import cz.raptors.dianotes.pages.templates.UnauthenticatedTemplate;
import cz.raptors.dianotes.service.UserService;
import cz.raptors.dianotes.service.exception.FailToHashPasswordException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.validation.EqualInputValidator;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * Provides ability to login into system.
 *
 * @author Jaroslav Strouhal
 */
@MountPath(alt ="/registration")
public class Registration extends UnauthenticatedTemplate {

    @SpringBean
    protected UserService userService;

    private Log LOGGER = LogFactory.getLog(Registration.class);

    private static final String REGISTRATION_BUTTON = "registrationButton";


    /**
     * Creates new instance of the Login page.
     *
     * @param parameters the page parameters, which is provided by wicket
     *                   framework
     */
    public Registration(final PageParameters parameters) {
        super(parameters);

        init();
    }

    private void init() {
        Form<?> registerForm = new RegisterForm("registerForm");
        registerForm.add(new Label("loginEmailLabel", getString("registrationForm.emailLabel")));
        registerForm.add(new Label("formPasswordLabel", getString("registrationForm.password")));
        registerForm.add(new Label("formPasswordLabel2", getString("registrationForm.password2")));
        registerForm.add(new Label("nameLabel", getString("registrationForm.name")));
        registerForm.add(new Label("surnameLabel", getString("registrationForm.surname")));
        add(registerForm);
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
        add(feedbackPanel);
    }


    private class RegisterForm extends Form<RegisterUserModel> {
        private RegisterForm(String id) {
            super(id, new CompoundPropertyModel<RegisterUserModel>(new RegisterUserModel()));
            initForm();
        }

        private void initForm() {
        	//.add(new StrongPasswordValidator())
            add(new EmailTextField("email").add(EmailAddressValidator.getInstance()));
            PasswordTextField passwordTextField = new PasswordTextField("password");
            add(passwordTextField.setRequired(true));
            PasswordTextField passwordTextField2 = new PasswordTextField("password2");
            add(passwordTextField2.setRequired(true));
            add(new TextField("firstName").setRequired(true));
            add(new TextField("lastName").setRequired(true));
            add(new EqualPasswordInputValidator(passwordTextField, passwordTextField2));
            add(new Button(REGISTRATION_BUTTON));

        }

        @Override
        protected void onSubmit() {
            RegisterUserModel registerUserModel = getModelObject();
            try {
                userService.registerUser(registerUserModel);
            } catch (FailToHashPasswordException e) {
                LOGGER.error(e.getMessage());
                error(e.getMessage());
            } catch (UnsupportedEncodingException e){
            	
            }
        }
    }


    /**
     * Login the user into system.
     */
    public void register() {
        /**
         * TODO: make a registration
         */
    }
}
