package cz.raptors.dianotes.pages.logged.results.basal;

import java.util.Date;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


/**
 * The basal panel, which is part of the results panel.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.results.Results
 */
public class Basal 
    extends Panel {

    private static final String SUGGESTIONS_ID = "suggestions";
    private static final String GRAPH_ID = "graph";
    
    private static Suggestions suggestions() {
        return new Suggestions(SUGGESTIONS_ID);
    }
    
    private static Graph graph() {
        return new Graph(GRAPH_ID);
    }
    
    private final Suggestions suggestions = suggestions();
    private final Graph graph = graph();
    
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

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(graph);
        add(suggestions);
    }
    
    
    
}
