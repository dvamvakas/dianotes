package cz.raptors.dianotes;

import cz.raptors.dianotes.pages.login.Login;
import org.apache.wicket.Session;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.core.util.crypt.KeyInSessionSunJceCryptFactory;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.caching.IResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.IStaticCacheableResource;
import org.apache.wicket.request.resource.caching.ResourceUrl;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see
 */
@Component
public class WicketApplication extends WebApplication {

    protected static final Logger LOGGER = LoggerFactory.getLogger(WicketApplication.class);
    private static final String DEFAULT_ENCODING = "UTF-8";
   // private static final String PACKAGE_TO_

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return Login.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new MySession(request);
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        new AnnotatedMountScanner().scanPackage("cz.raptors.dianotes").mount(this);
        getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);
        getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
        getSecuritySettings().setCryptFactory(new KeyInSessionSunJceCryptFactory());
        setRootRequestMapper(new CryptoMapper(getRootRequestMapper(), this));
        
       // mountPackage("", pageClass);
        

        LOGGER.debug("Wicket application up and running.");
    }
}
