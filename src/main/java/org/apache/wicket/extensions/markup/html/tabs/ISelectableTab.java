package org.apache.wicket.extensions.markup.html.tabs;

import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * The selectable tab, which extends the tab. The client of this tab could
 * register the selectable listener, which is used as decorator before the
 * getPanel returns the panel to the tabbed panel or the dynamic tabbed panel.
 *
 * @author Jaroslav Strouhal
 *
 * @see ITab the tab
 * @see ISelectableListener the selectable listener
 * @see TabbedPanel the tabbed panel
 * @see DynamicTabbedPanel the dynamic tabbed panel.
 */
public interface ISelectableTab<E extends WebMarkupContainer> extends ITab {

    /**
     * Adds the selectable listener to this tab.
     *
     * @param listener the selectable listener
     */
    void add(ISelectableListener<E> listener);

    /**
     * Removes the selectable listener from this tab.
     *
     * @param listener the selectable listener
     */
    void remove(ISelectableListener<E> listener);
  

    /**
     * Gets the selected panel by the wicket ID. The overriding of the method
     * force imlementator of this interface to returns panel of E type.
     *
     * @param id the wicket ID
     * @return the panel
     */
    @Override
    public E getPanel(String string);
}
