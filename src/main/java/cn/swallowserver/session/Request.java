package cn.swallowserver.session;

import cn.swallowserver.context.AttributeHolder;

import java.util.Date;

/**
 * @author Chen Haoming
 *         <p/>
 *         A request is a JSON-formatted string, like:
 *         <br/>
 *         {
 *         requestEncoding:"utf-8",
 *         sentTime:"GMT-80 2013-06-03 22:09:00.000",
 *         content:"Encoded String..."
 *         }
 *         <p/>
 *         The whole string is converted into a Request class,
 *         while "content" is decoded and then converted into an AttributeHoder class.
 */
public interface Request extends Interaction {

    AttributeHolder getParameters ();

    String getEncoding ();

    Date getSentTime ();

    AttributeHolder getAttributes ();
}
