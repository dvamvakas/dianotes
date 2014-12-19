package org.apache.wicket.extensions.markup.html.tabs;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * Abstract implementation of the selectable tab.
 *
 * @author Jaroslav Strouhal
 *
 * @see ISelectableTab
 */
public abstract class AbstractSelectableTab<E extends WebMarkupContainer> 
    implements ISelectableTab<E> {
 
    private final IModel<String> title;
    private boolean visible = true;
    private final List<ISelectableListener<E>> listeners;

    /**
     * Creates a new instance of the abstract selectable tab.
     * 
     * @param title the title
     */
    public AbstractSelectableTab(IModel<String> title) {
        this.title = title;
        listeners = new ArrayList<ISelectableListener<E>>();
    }

    @Override
    public void add(ISelectableListener<E> listener) {
        listeners.add(listener);
    }

    @Override
    public void remove(ISelectableListener<E> listener) {
        listeners.remove(listener);
    }

    @Override
    public final E getPanel(String id) {
        final E tab = getSelectedPanel(id);

        for(ISelectableListener<E> listener : listeners) {
            listener.onSelect(tab);
        }
        
        return tab;
    }

    /**
     * Gets the panel, which is used as selected panel.
     *
     * @param id the id
     * @return the selected panel; should not return null
     */
    protected abstract E getSelectedPanel(String id);

    @Override
    public boolean isVisible() {
        return visible;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    } 
    

    @Override
    public IModel<String> getTitle() {
        return title;
    }
}
