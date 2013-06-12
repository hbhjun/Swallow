package cn.swallowserver.nio;

import cn.swallowserver.session.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SocketChannel;

/**
 * @author Chen Haoming
 */
public class NIORequestImpl extends BaseRequest implements NIORequest {

    private static final transient Logger log = LoggerFactory.getLogger(NIORequestImpl.class);


    public NIORequestImpl(NIOSession session) {
        super(session);
    }

    @Override
    public SocketChannel getSocketChannel () {
        return ((NIOSession)getSession()).getSocketChannel();
    }

}
