package org.apache.wicket.extensions.markup.html.tabs;

/**
 * The listener is used for handle event of tab selection for ISelectableTab.
 * 
 * @author Jaroslav Strouhal
 * 
 * @param <E> the class of panel, which is generated by ISelectablePanel
 * 
 * @see ISelectableTab
 */
public interface ISelectableListener<E> {

    /**
     * Event, which is invoked when tab is selected.
     *
     * @param panel the panel, which is used as selected panel in markup
     */
    void onSelect(E panel);
}
