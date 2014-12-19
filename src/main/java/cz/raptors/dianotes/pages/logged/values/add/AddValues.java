package cz.raptors.dianotes.pages.logged.values.add;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * The add values panel, which is part of the values panel.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.values.Values
 */
public class AddValues 
    extends Panel {
 
    private static final String BOLUS_ID = "bolus";
    private static final String GLUCOSE_ID = "glucose";

    private final Bolus bolus = bolus();
    private final Glucose glucose = glucose();

    /**
     * Creates new instance of the add values panel.
     * 
     * @param id wicket id
     */
    public AddValues(final String id) {
        super(id);
    }

    /**
     * Creates a new instance of the add values panel, which will be setted up
     * by the model.
     * 
     * @param id wicket id
     * @param model the model
     */
    public AddValues(final String id, final IModel<?> model) {
        super(id, model);
    }
    
    private Bolus bolus() {
        return new Bolus(BOLUS_ID);
    }
    
    private Glucose glucose() {
        return new Glucose(GLUCOSE_ID);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(glucose);
        add(bolus);
    }
    
    
    
}
