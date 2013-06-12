package cn.swallowserver.session;

import java.util.Date;

/**
 * @author Chen Haoming
 */
public abstract class BaseRequest extends BaseInteraction implements Request {

    private AttributeHolder parameters;

    private AttributeHolder attributes = new BaseAttributeHolder ();

    private Date sentTime;

    private String requestEncoding;

    public BaseRequest (Session session) {
        super (session);
    }

    public AttributeHolder getParameters () {
        return parameters;
    }

    protected void setParameters (AttributeHolder parameters) {
        this.parameters = parameters;
    }

    public Date getSentTime () {
        return sentTime;
    }

    public void setSentTime (Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getEncoding () {
        return requestEncoding;
    }

    public void setRequestEncoding (String requestEncoding) {
        this.requestEncoding = requestEncoding;
    }

    public AttributeHolder getAttributes () {
        return attributes;
    }

}
