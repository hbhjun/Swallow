package cn.swallowserver.nio;

import cn.swallowserver.SwallowServer;
import cn.swallowserver.session.AttributeHolder;
import cn.swallowserver.session.BaseAttributeHolder;
import cn.swallowserver.session.Session;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * @author Chen Haoming
 */
public class NIOSession implements Session {

    private boolean valid = true;

    private SocketChannel socketChannel;

    private SelectionKey readKey;

    private SelectionKey writeKey;

    private AttributeHolder attributes = new BaseAttributeHolder ();

    private final Object validLock = new Object ();

    private SwallowServer server;

    public NIOSession (SocketChannel socketChannel, NIOServer server) {
        this.socketChannel = socketChannel;
        this.server = server;
    }

    public SocketChannel getSocketChannel () {
        return socketChannel;
    }

    @Override
    public AttributeHolder getAttributes () {
        return attributes;
    }

    @Override
    public boolean isValid () {
        synchronized (validLock) {
            return valid;
        }
    }

    @Override
    public void invalid () throws IOException {
        synchronized (validLock) {
            if (valid) {
                socketChannel.close ();
                this.valid = false;
                NIOServer server = (NIOServer) this.getServer ();
                Map<SocketChannel, NIOSession> map = server.getSocketChannelSessionMap ();
                map.remove (socketChannel);
            }
        }

    }

    @Override
    public SwallowServer getServer () {
        return this.server;
    }

    public static NIOSession create (Selector selector, ServerSocketChannel serverSocketChannel, NIOServer server) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept ();
        socketChannel.configureBlocking (false);
        NIOSession session = new NIOSession (socketChannel, server);
        session.readKey = socketChannel.register (selector, SelectionKey.OP_READ);
        session.writeKey = socketChannel.register (selector, SelectionKey.OP_WRITE);
        return session;
    }

}
