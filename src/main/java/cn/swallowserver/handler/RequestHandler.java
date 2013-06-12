package cn.swallowserver.handler;

import cn.swallowserver.session.Request;
import cn.swallowserver.session.Response;

/**
 * @author Chen Haoming
 */
public interface RequestHandler {

    void handle(Request request, Response response);
}
