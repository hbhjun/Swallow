package cn.swallowserver.session;

/**
 * @author Chen Haoming
 */
public abstract class BaseInteraction implements Interaction {

    private Session session;

    public BaseInteraction(Session session) {
        this.session = session;
    }

    @Override
    public Session getSession () {
        return session;
    }
}
