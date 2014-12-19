package cz.raptors.dianotes.config;

/**
 * Created by vamvda1 on 31.3.14.
 */
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.wicketstuff.servlet3.WicketFilter3;

@WebFilter(value = "/*", initParams = { @WebInitParam(name = "applicationFactoryClassName", value = "org.apache.wicket.spring.SpringWebApplicationFactory"), @WebInitParam(name = "wicket.configuration", value = "deployment") })
public final class WicketFilter extends WicketFilter3 {
    // empty
}