package cz.raptors.dianotes.pages.logged.values;

import cz.raptors.dianotes.pages.logged.content.AbstractContent;
import cz.raptors.dianotes.pages.logged.values.add.AddValues;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * The add values panel, which is part of main menu.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.Logged
 * @see cz.raptors.wicketsample.pages.logged.Menu
 */
public class Values
        extends AbstractContent {

    private static final String ADD_VALUES_LABEL = "menu.item.Values.AddValues.label";
    

    /**
     * Creates new instance of the panel.
     *
     * @param id wicket id
     */
    public Values(final String id) {
        super(id);
    }

    /**
     * Creates a new instance of the panel, which will be setted up by
     * the model.
     *
     * @param id wicket id
     * @param model the model
     */
    public Values(final String id, final IModel<?> model) {
        super(id, model);
    }

    @Override
    protected List<ITab> getTabPanels() {

        final List<ITab> tabs = new ArrayList<ITab>();

        tabs.add(getAddValues());
      
        return tabs;
    }
    
    @Override
    protected void onInitialize() {
    	
    	super.onInitialize();
    	
    	
    	
    }

    private ITab getAddValues() {
        return new AbstractTab(getResource(ADD_VALUES_LABEL)) {

            @Override
            public Panel getPanel(String panelId) {
                return getAddValues(panelId);
            }
        };
    }

    private Panel getAddValues(String id) {
        return new AddValues(id);
    }

}
