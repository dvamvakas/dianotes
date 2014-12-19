package cz.raptors.dianotes.pages.logged.results.glucose;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * The glucose panel, which is part of the results panel.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.results.Results
 */
public class Glucose 
    extends Panel {


    /**
     * Creates new instance of the results panel.
     * 
     * @param id wicket id
     */
    public Glucose(final String id) {
        super(id);
    }


    /**
     * Creates a new instance of the results panel, which will be setted up
     * by the model.
     * 
     * @param id wicket id
     * @param model the model
     */
    public Glucose(final String id, final IModel<?> model) {
        super(id, model);
    }
    
    
}
