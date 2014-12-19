package cz.raptors.dianotes.pages.logged.results;

import cz.raptors.dianotes.pages.logged.results.glucose.Glucose;
import cz.raptors.dianotes.pages.logged.results.basal.Basal;
import cz.raptors.dianotes.pages.logged.content.AbstractContent;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * The results panel, which is part of main menu.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.Logged
 * @see cz.raptors.wicketsample.pages.logged.Menu
 */
public class Results
        extends AbstractContent {

    private static final String BASAL_LABEL = "menu.item.Results.Basal.label";
    private static final String GLUCOSE_LABEL = "menu.item.Results.Glucose.label";

    /**
     * Creates new instance of the results panel.
     *
     * @param id wicket id
     */
    public Results(final String id) {
        super(id);
    }

    /**
     * Creates a new instance of the results panel, which will be setted up by
     * the model.
     *
     * @param id wicket id
     * @param model the model
     */
    public Results(final String id, final IModel<?> model) {
        super(id, model);
    }

    @Override
    protected List<ITab> getTabPanels() {

        final List<ITab> tabs = new ArrayList<ITab>();

        tabs.add(basal());
        tabs.add(glucose());

        return tabs;
    }

    private ITab basal() {
        return new AbstractTab(getResource(BASAL_LABEL)) {

            @Override
            public Panel getPanel(String panelId) {
                return basal(panelId);
            }
        };
    }

    private Panel basal(String id) {
        return new Basal(id);
    }

    private ITab glucose() {

        return new AbstractTab(getResource(GLUCOSE_LABEL)) {

            @Override
            public Panel getPanel(String panelId) {
                return glucose(panelId);
            }
        };
    }

    private Panel glucose(String id) {
        return new Glucose(id);
    }
}
