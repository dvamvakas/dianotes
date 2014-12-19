package cz.raptors.dianotes;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import java.util.Locale;

/**
 * Created by vamvda1 on 23.4.14.
 */
public class MySession extends WebSession {
    public MySession( Request req ) {
        super( req );
    }

    @Override
    public void setLocale( Locale locale ) {
        // your locale substitution code goes here, for example:
            super.setLocale( Locale.US);
    }

}