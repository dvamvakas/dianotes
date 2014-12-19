package cz.raptors.dianotes.pages.templates;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Abstract template, which is used by all application pages.
 *
 * @author Jaroslav Strouhal
 */
public abstract class UnauthenticatedTemplate extends BasicTemplate {

    /**
     * Creates new instance of the template page.
     */
    protected UnauthenticatedTemplate() {
        super();
    }

    /**
     * Creates a new instance of the template page, which will be setted up
     * by the page parameters.
     *
     * @param parameters the page parameters
     */
    public UnauthenticatedTemplate(final PageParameters parameters) {
        super(parameters);
    }

    /**
     * Creates a new instance of the template page, which will be setted up
     * by the model.
     * 
     * @param model the model
     */
    public UnauthenticatedTemplate(IModel<?> model) {
        super(model);
    }
}
