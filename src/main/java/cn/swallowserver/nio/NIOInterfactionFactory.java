package cn.swallowserver.nio;

import cn.swallowserver.SwallowServer;
import cn.swallowserver.session.InteractionFactory;
import cn.swallowserver.session.Request;
import cn.swallowserver.session.Session;

/**
 * @author ICMLucky
 */
public class NIOInterfactionFactory implements InteractionFactory {


    @Override
    public NIOResponse create (Request request) {
        return new NIOResponseImpl (request.getSession ());
    }

    @Override
    public NIORequest create (Session session, byte[] data) {
        throw new UnsupportedOperationException ();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public NIOSession create (SwallowServer server) {
        throw new UnsupportedOperationException ();
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
