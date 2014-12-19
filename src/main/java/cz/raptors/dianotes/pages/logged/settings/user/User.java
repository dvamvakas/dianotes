package cz.raptors.dianotes.pages.logged.settings.user;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * The user panel, which is part of the settings panel.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.settings.Settings
 */
public class User 
    extends Panel {


    /**
     * Creates new instance of the user panel.
     * 
     * @param id wicket id
     */
    public User(final String id) {
        super(id);
    }


    /**
     * Creates a new instance of the user panel, which will be setted up
     * by the model.
     * 
     * @param id wicket id
     * @param model the model
     */
    public User(final String id, final IModel<?> model) {
        super(id, model);
    }
    
    
}
