package cn.swallowserver;

import cn.swallowserver.session.AttributeHolder;
import cn.swallowserver.event.ServerEvenSource;

/**
 * @author Chen Haoming
 */
public interface SwallowServer extends ServerEvenSource {

    AttributeHolder getAttributes ();

    String getEncoding();

    void setEncoding(String encoding);
}
