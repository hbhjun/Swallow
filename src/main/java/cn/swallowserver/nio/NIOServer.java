package cn.swallowserver.nio;

import cn.swallowserver.ThreadTemplate;
import cn.swallowserver.SwallowServer;
import cn.swallowserver.session.AttributeHolder;
import cn.swallowserver.event.ServerEventNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Chen Haoming
 */
public class NIOServer extends ThreadTemplate implements SwallowServer {

    private static final transient Logger log = LoggerFactory.getLogger (NIOServer.class);

    public static final int PORT = 19999;
    public static final String UTF_8 = "UTF-8";

    private Selector selector;

    private NIOReader reader;
    private NIOWriter writer;

    private Map<SocketChannel, NIOSession> socketChannelSessionMap = new ConcurrentHashMap<SocketChannel, NIOSession> ();

    AttributeHolder serverContext;

    private String encoding = UTF_8;

    public NIOServer () throws IOException {

        selector = Selector.open ();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open ();
        serverSocketChannel.configureBlocking (false);
        ServerSocket serverSocket = serverSocketChannel.socket ();
        serverSocket.bind (new InetSocketAddress (PORT));
        serverSocketChannel.register (selector, SelectionKey.OP_ACCEPT);

        reader = new NIOReader (this);
        writer = new NIOWriter (this);
    }

    @Override
    protected void preRunning () {
        reader.start ();
        writer.start ();

    }

    @Override
    protected void running () throws InterruptedException {
        try {
            int len = selector.select (DEFAULT_TIMEOUT);

            if (len > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys ().iterator ();
                log.debug ("{} SelectionKeys has been selected.", len);

                while (iterator.hasNext ()) {
                    SelectionKey key = iterator.next ();
                    iterator.remove ();

                    if (key.isValid ()) {
                        if (key.isAcceptable()) {
                            ServerSocketChannel channel = (ServerSocketChannel) key.channel ();
                            NIOSession session = NIOSession.create (selector, channel, this);
                            socketChannelSessionMap.put (session.getSocketChannel (), session);
                            //notifier.fireAccepted(new Event(key));
                        }

                        if (key.isReadable()) {
                            reader.read (key);
                            //notifier.fireRead(new Event(key));
                        }
                    } else {
                        log.warn ("Key[{}] is invalid.", key);
                        key.cancel ();
                    }

                }
            }

            // todo: deal with timeout;
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    protected void postRunning () {
        reader.stopThread ();
        writer.stopThread ();
    }

    public String getEncoding () {
        return encoding;
    }

    public void setEncoding (String encoding) {
        this.encoding = encoding;
    }

    public Map<SocketChannel, NIOSession> getSocketChannelSessionMap() {
        return socketChannelSessionMap;
    }

    @Override
    public AttributeHolder getAttributes () {
        throw new UnsupportedOperationException();  //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void register(ServerEventNotifier notifier) {
        throw new UnsupportedOperationException();  //To change body of created methods use File | Settings | File Templates.
    }
}
