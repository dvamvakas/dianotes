package cz.raptors.dianotes.pages.logged.content;

import java.util.List;
import org.apache.wicket.extensions.markup.html.tabs.DynamicTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;


/**
 * The tab content changes markup of the dynamic tabbed panel. 
 *
 * @author Jaroslav Strouhal
 */
public class TabContent extends DynamicTabbedPanel {

    private static final String CONTENT_ID = "content";
    

    /**
     * Creates new instance of the tab content.
     * 
     * @param id wicket id
     */
    public TabContent(final String id, final List<ITab> tabs) {
        super(id, tabs);
    }

    @Override
    public String getTabPanelId() {
        return CONTENT_ID;
    }
    
    @Override
    protected String getSelectedTabPanelCssSuffix() {
        return " on";
    }
    
}
