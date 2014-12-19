package cz.raptors.dianotes.pages.templates;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Abstract template, which is used by all application pages.
 *
 * @author Jaroslav Strouhal
 */
public abstract class BasicTemplate extends WebPage {	
	
    /**
     * Creates new instance of the template page.
     */
    public BasicTemplate() {
        super();
    }

    /**
     * Creates a new instance of the template page, which will be setted up
     * by the page parameters.
     *
     * @param parameters the page parameters
     */
    public BasicTemplate(final PageParameters parameters) {
        super(parameters);
    }

    /**
     * Creates a new instance of the template page, which will be setted up
     * by the model.
     * 
     * @param model the model
     */
    public BasicTemplate(IModel<?> model) {
        super(model);
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
