package cz.raptors.dianotes.pages.logged.settings;

import cz.raptors.dianotes.pages.logged.settings.password.Password;
import cz.raptors.dianotes.pages.logged.settings.basal.Basal;
import cz.raptors.dianotes.pages.logged.settings.user.User;
import cz.raptors.dianotes.pages.logged.content.AbstractContent;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * The settings panel, which is part of main menu.
 *
 * @author Jaroslav Strouhal
 * 
 * @see cz.raptors.wicketsample.pages.logged.Logged
 * @see cz.raptors.wicketsample.pages.logged.Menu
 */
public class Settings
        extends AbstractContent {

    private static final String BASAL_LABEL = "menu.item.Settings.Basal.label";
    private static final String USER_LABEL = "menu.item.Settings.User.label";
    private static final String PASSWORD_LABEL = "menu.item.Settings.Password.label";

    private final ITab basal = basal();
    private final ITab user = user();
    private final ITab password = password();
    
    
    /**
     * Creates new instance of the panel.
     *
     * @param id wicket id
     */
    public Settings(final String id) {
        super(id);
    }

    /**
     * Creates a new instance of the panel, which will be setted up by
     * the model.
     *
     * @param id wicket id
     * @param model the model
     */
    public Settings(final String id, final IModel<?> model) {
        super(id, model);
    }
    
    /**
     * Navigates to user settings panel
     */
    public void toUserSettings() {
        setSelectedTab(user);
    }

    @Override
    protected List<ITab> getTabPanels() {

        final List<ITab> tabs = new ArrayList<ITab>();

        tabs.add(basal);
        tabs.add(user);
        tabs.add(password);

        return tabs;
    }

    private ITab basal() {
        return new AbstractTab(getResource(BASAL_LABEL)) {

            @Override
            public Panel getPanel(String panelId) {
                return new Basal(panelId);
            }
        };
    }

    private ITab user() {

        return new AbstractTab(getResource(USER_LABEL)) {

            @Override
            public Panel getPanel(String panelId) {
                return new User(panelId);
            }
        };
    }
 
    private ITab password() {

        return new AbstractTab(getResource(PASSWORD_LABEL)) {

            @Override
            public Panel getPanel(String panelId) {
                return new Password(panelId);
            }
        };
    }

}
