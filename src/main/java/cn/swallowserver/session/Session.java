package cn.swallowserver.session;

import cn.swallowserver.SwallowServer;
import cn.swallowserver.context.AttributeHolder;

import java.io.IOException;


public interface Session {

    AttributeHolder getAttributes ();

    boolean isValid ();

    void invalid () throws IOException;

    SwallowServer getServer();
}
