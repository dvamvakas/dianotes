package cz.raptors.dianotes.pages.logged.settings.basal;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * The basal panel, which is part of the settings panel.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.settings.Settings
 */
public class Basal 
    extends Panel {

    /**
     * Creates new instance of the basal panel.
     * 
     * @param id wicket id
     */
    public Basal(final String id) {
        super(id);
    }


    /**
     * Creates a new instance of the basal panel, which will be setted up
     * by the model.
     * 
     * @param id wicket id
     * @param model the model
     */
    public Basal(final String id, final IModel<?> model) {
        super(id, model);
    }
    
    
}
