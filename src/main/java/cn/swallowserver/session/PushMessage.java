package cn.swallowserver.session;

import java.io.InputStream;

/**
 * @author ICMLucky
 */
public interface PushMessage extends Interaction {

    InputStream getMessage ();
}
