package cn.swallowserver.session;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author Chen Haoming
 */
public interface Interaction {

    Session getSession ();
}
