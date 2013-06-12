package cn.swallowserver.session;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Chen Haoming
 *         <p/>
 *         A response is a JSON-formatted string, ex.,
 *         {
 *         responseCode:0,
 *         responseTime:"GMT-80 2013-06-03 22:09:00.000",
 *         content:"encoded string..."
 *         }
 */
public interface Response extends Interaction {

    OutputStream getOut ();

    boolean isClosed ();

    void close () throws IOException;

    void setEncoding(String encoding);
}
