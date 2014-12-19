package cz.raptors.dianotes.pages.logged;

import cz.raptors.dianotes.pages.login.Login;
import java.util.List;
import org.apache.wicket.extensions.markup.html.tabs.DynamicTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.link.Link;

/**
 *
 * @author Jaroslav Strouhal
 */
public abstract class Menu extends DynamicTabbedPanel {
    
    private static final String CONTENT_ID = "content";
    private static final String USER_ID = "user";
    private static final String LOGOUT_ID = "logout";
    
    
    
    public Menu(String id, List<ITab> tabs) {
        super(id, tabs);
        
        
    }
    
    
    
    
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(logoutLink());
        add(userLink());
    }
    
    
    
    private Link logoutLink() {
        return new Link(LOGOUT_ID) {

            @Override
            public void onClick() {
                logout();
            }
        };
    }
    
    private Link userLink() {
        return new Link(USER_ID) {

            @Override
            public void onClick() {
                onUserSettings();
            }
        };
    }
    
    /**
     * Action, which is called after user click to user
     */
    public abstract void onUserSettings();
    
    public void logout() {
        /**
         * TODO: to add logout logic here
         */
        setResponsePage(Login.class);
    } 

    @Override
    public String getTabPanelId() {
        return CONTENT_ID;
    }

    @Override
    protected String getTabPanelCss() {
        return "no0";
    }

    @Override
    protected String getSelectedTabPanelCssSuffix() {
        return "on";
    }
    
    
}
