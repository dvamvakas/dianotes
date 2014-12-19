package cz.raptors.dianotes.pages.login;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import cz.raptors.dianotes.pages.logged.Logged;
import cz.raptors.dianotes.pages.registration.Registration;
import cz.raptors.dianotes.pages.templates.UnauthenticatedTemplate;
import cz.raptors.dianotes.service.UserService;
import cz.raptors.dianotes.service.exception.FailToHashPasswordException;
import cz.raptors.dianotes.service.exception.FailedToLoginUserException;
import cz.raptors.dianotes.service.exception.UnknownEmailException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * Provides ability to login into system.
 *
 * @author Jaroslav Strouhal
 */
@MountPath(value ="/login")
public class Login extends UnauthenticatedTemplate {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1548199308835895074L;
	private static final String LOGIN_BUTTON = "loginButton";
    private static final String REGISTRATION_BUTTON = "registrationButton";
    
    private String email;
    private String password;
    
    @Inject
    protected UserService userService;

    /**
     * Creates new instance of the Login page.
     *
     * @param parameters the page parameters, which is provided by wicket
     * framework
     */
    public Login(final PageParameters parameters) {
        super(parameters);
    }
    
    @Override
    protected void onInitialize(){
    	super.onInitialize();
    	StatelessForm loginForm = new StatelessForm("registerForm")
		{
			@Override
			protected void onSubmit() {
					//try {
						//userService.loginUser(email, password);
						navigateToLogged();
					//} catch (FailedToLoginUserException e) {
					//	error(getString("LoginPage.errorInCombinationEmailPassword"));
					//} catch (UnknownEmailException e) {

					//} catch (FailToHashPasswordException e) {

					//}catch (UnsupportedEncodingException e){

					//}
			};
		};
		loginForm.setDefaultModel(new CompoundPropertyModel(this));
        loginForm.add(new Label("loginEmailLabel", getString("LoginPage.loginEmail")));
        loginForm.add(new EmailTextField("email").add(EmailAddressValidator.getInstance()));
        loginForm.add(new Label("loginPasswordLabel", getString("LoginPage.loginPassword")));
        loginForm.add(new PasswordTextField("password").setRequired(true));
        add(loginForm);
        add(new FeedbackPanel("feedbackPanel"));

        Link registerButton = new Link("registerNewUser"){
			@Override
			public void onClick() {
				navigateToRegistration();
			};
        };
        registerButton.add(new Label("registerNewUserLabel", getString("LoginPage.registerButton")));
        add(registerButton);
    }


    /**
     * Navigates to registration page.
     */
    public void navigateToRegistration() {
        setResponsePage(Registration.class);
    }
    
    /**
     * Navigates to logged page.
     */
    public void navigateToLogged() {
        setResponsePage(Logged.class);
    }
}
