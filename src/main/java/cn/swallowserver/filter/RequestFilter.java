package cn.swallowserver.filter;

import cn.swallowserver.session.Request;
import cn.swallowserver.session.Response;

/**
 * @author Chen Haoming
 */
public interface RequestFilter {

    void filter (Request request, Response response);
}
