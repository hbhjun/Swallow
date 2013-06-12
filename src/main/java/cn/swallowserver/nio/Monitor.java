package cn.swallowserver.nio;

import cn.swallowserver.ThreadTemplate;

/**
 * @author Chen Haoming
 */
public class Monitor extends ThreadTemplate {

    private NIOServer server;

    Monitor (NIOServer server) {
       this.server = server;
    }

    @Override
    protected void running () throws InterruptedException {
        server.getSocketChannelSessionMap ();
    }
}
