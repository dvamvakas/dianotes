package cz.raptors.dianotes.pages.logged.settings.password;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * The password panel, which is part of the settings panel.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.settings.Settings
 */
public class Password 
    extends Panel {

    /**
     * Creates new instance of the password panel.
     * 
     * @param id wicket id
     */
    public Password(final String id) {
        super(id);
    }


    /**
     * Creates a new instance of the password panel, which will be setted up
     * by the model.
     * 
     * @param id wicket id
     * @param model the model
     */
    public Password(final String id, final IModel<?> model) {
        super(id, model);
    }
    
    
}
