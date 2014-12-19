package cz.raptors.dianotes.pages.logged.content;

import java.io.Serializable;
import java.util.List;
import org.apache.wicket.extensions.markup.html.tabs.DynamicTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * Abstract content template, which is used by all content panels. The content
 * contains tabs, which are defined by implemention of this abstract content.
 *
 * @author Jaroslav Strouhal
 *
 * @see #setSelectedTab(org.apache.wicket.extensions.markup.html.tabs.ITab)
 */
public abstract class AbstractContent
        extends Panel {

    private static final String TAB_CONTENT_ID = "tabContent";

    /**
     * Strategy, which is used for different statuses of the class.
     */
    private interface Strategy extends Serializable {

        /**
         * The method is called after object is fully initialized.
         *
         * @see Content#onInitialize()
         */
        void init();

        /**
         * The method is called if the user wants selet the tab.
         *
         * @param tab the tab
         *
         * @see
         * Content#setSelectedTab(org.apache.wicket.extensions.markup.html.tabs.ITab)
         */
        void setSelectedTab(ITab tab);
    }

    /**
     * Strategy, which is used before class is fully initialized.
     *
     * @see #onInitialize()
     */
    private class NotInitialized implements Strategy {

        
        private ITab selectedTab;

        @Override
        public void init() {
            if (selectedTab != null) {
                tabbedPanel.setSelectedTab(selectedTab);
            }
        }

        @Override
        public void setSelectedTab(ITab tab) {
            selectedTab = tab;
        }
    }

    /**
     * Strategy, which is used after class is fully initialized.
     *
     * @see #onInitialize()
     */
    private class Initialized implements Strategy {

        @Override
        public void init() {
        }

        @Override
        public void setSelectedTab(ITab tab) {
            tabbedPanel.setSelectedTab(tab);
        }
    }
    /**
     * The class is not initialized in constuction time.
     */
    private Strategy strategy = new NotInitialized();
    private DynamicTabbedPanel tabbedPanel;

    /**
     * Creates new instance of the results panel.
     *
     * @param id wicket id
     */
    public AbstractContent(final String id) {
        super(id);
    }

    /**
     * Creates a new instance of the results panel, which will be setted up by
     * the model.
     *
     * @param id wicket id
     * @param model the model
     */
    public AbstractContent(final String id, final IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        tabbedPanel = tabContent();

        add(tabbedPanel);

        strategy.init();
        strategy = new Initialized();
    }

    private TabContent tabContent() {
        return new TabContent(TAB_CONTENT_ID, getTabPanels());
    }

    /**
     * Gets all tab panels, which should by in content page.
     *
     * @return all tab panels; should not return null
     */
    protected abstract List<ITab> getTabPanels();

    /**
     * Set the tab as selected tab in tabbed panel.
     *
     * @param tab the tab
     *
     * @throws NullPointerException if the tab is null
     * @throws IllegalArgumentException if the tab is not part of tabbed panel
     * tabs.
     *
     * @see
     * DynamicTabbedPanel#setSelectedTab(org.apache.wicket.extensions.markup.html.tabs.ITab)
     */
    protected void setSelectedTab(ITab tab) {
        //tabbedPanel.setSelectedTab(tab);
        strategy.setSelectedTab(tab);
    }

    /**
     * Gets resource from properties.
     *
     * @param resourceId the resource id is in fact name of the property
     * @return the property value
     */
    public final IModel<String> getResource(final String resourceId) {
        return new StringResourceModel(resourceId, this, null);
    }
}
