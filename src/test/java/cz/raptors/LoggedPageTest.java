package cz.raptors;

import cz.raptors.dianotes.WicketApplication;
import cz.raptors.dianotes.pages.logged.Logged;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class LoggedPageTest {

    private WicketTester tester;
    
    private Logged logged;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
        //start and render the test page
        logged = tester.startPage(Logged.class);
    }
    
    @After
    public void tearDown() {
        tester = null;
        logged = null;
    }

    @Test
    public void rendersSuccessfully() {
        tester.assertRenderedPage(Logged.class);
    }
}
