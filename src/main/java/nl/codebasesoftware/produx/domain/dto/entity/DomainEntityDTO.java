package nl.codebasesoftware.produx.domain.dto.entity;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class DomainEntityDTO {

    @Override
    public boolean equals(Object obj) {
        return IdEquality.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return IdHashCode.hashCode(this);
    }

    protected abstract Long getId();
}

