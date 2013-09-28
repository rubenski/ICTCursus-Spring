package nl.codebasesoftware.produx.domain.dto.entity;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 13:00
 * To change this template use File | Settings | File Templates.
 */
public class IdEquality {

    public static <T extends DomainEntityDTO> boolean equals(T entity, Object other) {
        if (entity == other) return true;
        if (other == null || entity == null || entity.getClass() != other.getClass()) return false;
        if (!(other.getClass() == entity.getClass())) return false;
        T otherEntity = (T) other;
        return entity.getId().equals(otherEntity.getId());
    }
}
