package cn.swallowserver.interation;

import cn.swallowserver.nio.NIOInterfactionFactory;
import cn.swallowserver.session.Request;
import cn.swallowserver.session.Response;
import cn.swallowserver.session.Session;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertSame;

/**
 * @author ICMLucky
 */
@RunWith (JMock.class)
public class NIOResponseFactoryTest {

    private JUnit4Mockery jUnit4Mockery = new JUnit4Mockery ();

    @Test
    public void testCreate () {
        final Request request = jUnit4Mockery.mock (Request.class);
        final Session session = jUnit4Mockery.mock (Session.class);

        jUnit4Mockery.checking (new Expectations () {
            {
                allowing (request).getSession ();
                will (returnValue (session));
            }
        });


        NIOInterfactionFactory factory = new NIOInterfactionFactory () ;

        Response response = factory.create (request);

        assertNotNull (response);
        assertSame (request.getSession (), response.getSession ());
    }
}
