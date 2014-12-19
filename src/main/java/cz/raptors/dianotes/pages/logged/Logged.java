package cz.raptors.dianotes.pages.logged;

import cz.raptors.dianotes.pages.logged.results.Results;
import cz.raptors.dianotes.pages.logged.settings.Settings;
import cz.raptors.dianotes.pages.logged.values.Values;
import cz.raptors.dianotes.pages.templates.BasicTemplate;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.tabs.AbstractSelectableTab;
import org.apache.wicket.extensions.markup.html.tabs.ISelectableListener;
import org.apache.wicket.extensions.markup.html.tabs.ISelectableTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The page, which is used for logged user.
 *
 * @author Jaroslav Strouhal
 */
public class Logged
        extends BasicTemplate {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8738385053077634956L;
	private static final String RESULTS_LABEL = "menu.item.Results.label";
    private static final String VALUES_LABEL = "menu.item.AddValues.label";
    private static final String SETTINGS_LABEL = "menu.item.Settings.label";
    private static final String MENU_ID = "menu";
    
    private final ISelectableTab<Results> results = results();
    private final ISelectableTab<Values> values = values();
    private final ISelectableTab<Settings> settings = settings();
            
    private Menu menu;

    /**
     * Creates new instance of the logged page.
     */
    public Logged() {
        super();
    }

    /**
     * Creates a new instance of the logged page, which will be setted up by
     * the page parameters.
     *
     * @param parameters the page parameters
     */
    public Logged(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        menu = menu();

        add(menu);
    }

    private Menu menu() {
        return new Menu(MENU_ID, menuTabs()) {

            @Override
            public void onUserSettings() {
                Logged.this.toUserSettings();
            }
        };
    }

    private List<ITab> menuTabs() {

        final List<ITab> tabs = new ArrayList();

        tabs.add(results);
        tabs.add(values);
        tabs.add(settings);

        return tabs;
    }

    private void toUserSettings() {
        menu.setSelectedTab(settings, userSettingsListener());
    }
    
    private ISelectableListener<Settings> userSettingsListener() {
           return new ISelectableListener<Settings>(){

            @Override
            public void onSelect(Settings panel) {
                panel.toUserSettings();
            }
        };
   }

    private ISelectableTab<Results> results() {
        return new AbstractSelectableTab<Results>(getResource(RESULTS_LABEL)) {

            @Override
            public Results getSelectedPanel(String panelId) {
                return new Results(panelId);
            }
        };
    }

    private ISelectableTab<Values> values() {
        return new AbstractSelectableTab<Values>(getResource(VALUES_LABEL)) {

            @Override
            public Values getSelectedPanel(String panelId) {
                return new Values(panelId);
            }
        };
    }

    private ISelectableTab<Settings> settings() {
        return new AbstractSelectableTab<Settings>(getResource(SETTINGS_LABEL)) {

            @Override
            public Settings getSelectedPanel(String panelId) {
                return new Settings(panelId);
            }
        };
    }
   
}
