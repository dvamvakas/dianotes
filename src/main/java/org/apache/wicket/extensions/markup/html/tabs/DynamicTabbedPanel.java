/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.extensions.markup.html.tabs;

import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TabbedPanel component represets a panel with tabs that are used to switch
 * between different content panels inside the TabbedPanel panel. <p>
 * <b>Note:</b> When the currently selected tab is replaced by changing the
 * underlying list of tabs, the change is not picked up unless a call is made to {@link #setSelectedTab(int)}.
 * <p> Example:
 *
 * <pre>
 * List tabs=new ArrayList();
 * tabs.add(new AbstractTab(new Model&lt;String&gt;(&quot;first tab&quot;)) {
 *   public Panel getPanel(String panelId)
 *   {
 *     return new TabPanel1(panelId);
 *   }
 * });
 *
 * tabs.add(new AbstractTab(new Model&lt;String&gt;(&quot;second tab&quot;)) {
 *   public Panel getPanel(String panelId)
 *   {
 *     return new TabPanel2(panelId);
 *   }
 * });
 *
 * add(new TabbedPanel(&quot;tabs&quot;, tabs));
 *
 * &lt;span wicket:id=&quot;tabs&quot; class=&quot;tabpanel&quot;&gt;[tabbed panel will be here]&lt;/span&gt;
 * </pre> <p> For a complete example see the component references in
 * wicket-examples project
 *
 * @see org.apache.wicket.extensions.markup.html.tabs.ITab
 *
 * @author Igor Vaynberg (ivaynberg at apache dot org)
 */
public class DynamicTabbedPanel extends Panel {

    private static final long serialVersionUID = 1L;
    
    private static class PanelHolder<E> implements ISelectableListener<E> {

        private E panel;
        
        @Override
        public void onSelect(E panel) {
            this.panel = panel;
        }
        
    }
    
    /**
     * id used for child panels
     */
    private static final String TAB_PANEL_ID = "panel";
    private final List<ITab> tabs;
    private transient Boolean[] tabsVisibilityCache;

    /**
     * Constructor
     *
     * @param id component id
     * @param tabs list of ITab objects used to represent tabs
     */
    public DynamicTabbedPanel(String id, List<ITab> tabs) {
        super(id, new Model<Integer>(new Integer(-1)));

        if (tabs == null) {
            throw new IllegalArgumentException("argument [tabs] cannot be null");
        }

        this.tabs = tabs;

        final IModel<Integer> tabCount = new AbstractReadOnlyModel<Integer>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Integer getObject() {
                return DynamicTabbedPanel.this.tabs.size();
            }
        };

        WebMarkupContainer tabsContainer = new WebMarkupContainer("tabs-container") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("class", getTabContainerCssClass());
            }
        };
        add(tabsContainer);

        // add the loop used to generate tab names
        tabsContainer.add(new Loop("tabs", tabCount) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(LoopItem item) {
                final int index = item.getIndex();
                final ITab tab = (DynamicTabbedPanel.this.tabs.get(index));

                final WebMarkupContainer titleLink = newLink("link", index);

                titleLink.add(newTitle("title", tab.getTitle(), index));
                item.add(titleLink);
            }

            @Override
            protected LoopItem newItem(int iteration) {
                return newTabContainer(iteration);
            }
        });
    }

    /**
     * Generates a loop item used to represent a specific tab's
     * <code>li</code> element.
     *
     * @param tabIndex
     * @return new loop item
     */
    protected LoopItem newTabContainer(final int tabIndex) {
        return new LoopItem(tabIndex) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                String cssClass = (String) tag.getAttribute("class");

                if (cssClass == null) {
                    cssClass = " ";
                }
                cssClass += " " + getTabPanelCss() + (getIndex() + 1);

                if (getIndex() == getSelectedTab()) {
                    cssClass += getSelectedTabPanelCssSuffix();
                }
                if (getIndex() == getTabs().size() - 1) {
                    cssClass += " last";
                }
                tag.put("class", cssClass.trim());
            }

            @Override
            public boolean isVisible() {
                return getTabs().get(tabIndex).isVisible();
            }
        };
    }

    /**
     * @see org.apache.wicket.Component#onBeforeRender()
     */
    @Override
    protected void onBeforeRender() {
        if (getSelectedTab() == -1 || isTabVisible(getSelectedTab()) == false) {
            // find first visible selected tab
            int selected = 0;
            for (int i = 0; i < tabs.size(); i++) {
                if (isTabVisible(i)) {
                    selected = i;
                    break;
                }
            }

            if (selected == tabs.size()) {
                /*
                 * none of the tabs are selected...
                 *
                 * we do not need to do anything special because the check in
                 * setSelectedTab() will replace the current tab panel with an
                 * empty one
                 */
                selected = 0;
            }

            setSelectedTab(selected);
        }

        super.onBeforeRender();
    }

    /**
     * @return the value of css class attribute that will be added to a div
     * containing the tabs. The default value is
     * <code>tab-row</code>
     */
    protected String getTabContainerCssClass() {
        return "tab-row";
    }

    /**
     * @return list of tabs that can be used by the user to add/remove/reorder
     * tabs in the panel
     */
    public final List<ITab> getTabs() {
        return tabs;
    }

    /**
     * Factory method for tab titles. Returned component can be anything that
     * can attach to span tags such as a fragment, panel, or a label
     *
     * @param titleId id of title component
     * @param titleModel model containing tab title
     * @param index index of tab
     * @return title component
     */
    protected Component newTitle(String titleId, IModel<?> titleModel, int index) {
        return new Label(titleId, titleModel);
    }

    /**
     * Factory method for links used to switch between tabs.
     *
     * The created component is attached to the following markup. Label
     * component with id: title will be added for you by the tabbed panel.
     *
     * <pre>
     * &lt;a href=&quot;#&quot; wicket:id=&quot;link&quot;&gt;&lt;span wicket:id=&quot;title&quot;&gt;[[tab title]]&lt;/span&gt;&lt;/a&gt;
     * </pre>
     *
     * Example implementation:
     *
     * <pre>
     * protected WebMarkupContainer newLink(String linkId, final int index)
     * {
     *  return new Link(linkId)
     *  {
     *    private static final long serialVersionUID = 1L;
     *
     *    public void onClick()
     *    {
     *      setSelectedTab(index);
     *    }
     *  };
     * }
     * </pre>
     *
     * @param linkId component id with which the link should be created
     * @param index index of the tab that should be activated when this link is
     * clicked. See
     *            {@link #setSelectedTab(int)}.
     * @return created link component
     */
    protected WebMarkupContainer newLink(String linkId, final int index) {
        return new Link(linkId) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setSelectedTab(index);
            }
        };
    }

    /**
     * sets the selected tab
     *
     * @param tab the tab, which is part of the registered tabs
     *
     * @throws NullPointerException if the tab is null
     * @throws IllegalArgumentException if the tab is not part of tabbed panel
     * tabs.
     */
    public void setSelectedTab(ITab tab) {
        if (isValidIndex(tabs.indexOf(tab))) {
            setSelectedTab(tabs.indexOf(tab));
        } else {
            throw new IllegalArgumentException(String.format(
                    "The tab %s is not part of tabbed panel tabs", tab));
        }
    }

    /**
     * Sets the selected tab and applicate the listener for the panel, which is
     * returned from getPanel method. The method onSelect is invoked on the
     * listener.
     *
     * @param index index of the tab to select
     *
     * @throws NullPointerException if the tab is null
     * @throws IllegalArgumentException if the tab is not part of tabbed panel
     * tabs.
     *
     * @see ISelectableTab#getPanel(java.lang.String)
     * @see ISelectableListener#onSelect(java.lang.Object)
     */
    public <E extends WebMarkupContainer> void setSelectedTab(ISelectableTab<E> tab,
            ISelectableListener<E> listener) {
        
        PanelHolder<E> panelHolder = new PanelHolder<E>();
        
        if (isValidIndex(tabs.indexOf(tab))) {
            tab.add(panelHolder);
            setSelectedTab(tabs.indexOf(tab));
            
            tab.remove(panelHolder);
            
            if(panelHolder.panel != null) {
                listener.onSelect(panelHolder.panel);
            }
        } else {
            throw new IllegalArgumentException(String.format(
                    "The tab %s is not part of tabbed panel tabs", tab));
        }
    }
   
   

    /**
     * Sets the selected tab.
     *
     * @param index index of the tab to select
     *
     * @throws IndexOutOfBoundsException if the index is less than 0 or index is
     * higher or equals to tabs.size()
     */
    public void setSelectedTab(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }

        setDefaultModelObject(new Integer(index));

        final Component component;

        if (tabs.isEmpty() || !isTabVisible(index)) {
            // no tabs or the currently selected tab is not visible
            component = new WebMarkupContainer(getTabPanelId());
        } else {
            // show panel from selected tab
            ITab tab = tabs.get(index);
            component = tab.getPanel(getTabPanelId());
            if (component == null) {
                throw new WicketRuntimeException("ITab.getPanel() returned null. TabbedPanel ["
                        + getPath() + "] ITab index [" + index + "]");

            }
        }

        if (!component.getId().equals(getTabPanelId())) {
            throw new WicketRuntimeException(
                    "ITab.getPanel() returned a panel with invalid id ["
                    + component.getId()
                    + "]. You must always return a panel with id equal to the provided panelId parameter. TabbedPanel ["
                    + getPath() + "] ITab index [" + index + "]");
        }

        getPanelParent().addOrReplace(component);
    }
    
    private boolean isValidIndex(final int index) {
        return index >= 0 && index < tabs.size();
    }

    /**
     * @return MarkupContainer
     */
    public MarkupContainer getPanelParent() {
        return this;
    }

    /**
     * The css, which is used to generation of css for each panel link
     *
     * @return
     */
    protected String getTabPanelCss() {
        return "tab";
    }

    /**
     * Suffix of the selected tab panel
     *
     * @return default implementation returns " selected"
     */
    protected String getSelectedTabPanelCssSuffix() {
        return " selected";
    }

    /**
     * @return String
     */
    public String getTabPanelId() {
        return TAB_PANEL_ID;
    }

    /**
     * @return index of the selected tab
     */
    public final int getSelectedTab() {
        return (Integer) getDefaultModelObject();
    }

    private boolean isTabVisible(int tabIndex) {
        if (tabsVisibilityCache == null) {
            tabsVisibilityCache = new Boolean[tabs.size()];
        }

        Boolean visible = tabsVisibilityCache[tabIndex];
        if (visible == null) {
            visible = tabs.get(tabIndex).isVisible();
            tabsVisibilityCache[tabIndex] = visible;
        }
        return visible;
    }

    @Override
    protected void onDetach() {
        tabsVisibilityCache = null;
        super.onDetach();
    }

    /**
     * @param tabPanelAndParentClasses
     * @return PageParameters
     */
    public static PageParameters getPageParameters(
            Class<? extends Panel>... tabPanelAndParentClasses) {

        PageParameters pageParameters = new PageParameters();

        Class<? extends Panel> parentTabPanelClass = null;

        for (Class<? extends Panel> tabPanelClass : tabPanelAndParentClasses) {
            if (parentTabPanelClass != null) {
                pageParameters = pageParameters.set(parentTabPanelClass.getSimpleName(),
                        tabPanelClass.getSimpleName());
            }

            parentTabPanelClass = tabPanelClass;
        }

        return pageParameters;
    }

    /**
     * @param id
     * @param pageClass
     * @param tabPanelAndParentClasses
     * @return BookmarkablePageLink
     */
    public static BookmarkablePageLink<Void> getBookmarkableTabLink(String id, Class<? extends WebPage> pageClass, Class<? extends Panel>... tabPanelAndParentClasses) {
        return new BookmarkablePageLink<Void>(id, pageClass, getPageParameters(tabPanelAndParentClasses));
    }
}
