package cz.raptors.dianotes.pages.logged.results.basal;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * The graph panel, which is part of the basal panel.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.results.basal.Basal
 */
public class Graph 
    extends Panel {


    /**
     * Creates new instance of the suggestions panel.
     * 
     * @param id wicket id
     */
    public Graph(final String id) {
        super(id);
    }


    /**
     * Creates a new instance of the suggestions panel, which will be setted up
     * by the model.
     * 
     * @param id wicket id
     * @param model the model
     */
    public Graph(final String id, final IModel<?> model) {
        super(id, model);
    }
    
    
}
