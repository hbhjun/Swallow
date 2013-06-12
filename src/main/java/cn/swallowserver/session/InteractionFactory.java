package cn.swallowserver.session;

import cn.swallowserver.SwallowServer;

/**
 * @author Chen Haoming
 */
public interface InteractionFactory {

    Response create (Request request);

    Request create (Session session, byte[] data);

    Session create (SwallowServer server);
}
