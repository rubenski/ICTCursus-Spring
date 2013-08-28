package nl.codebasesoftware.produx.domain.dto.entity;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 13:14
 * To change this template use File | Settings | File Templates.
 */
public class IdHashCode {

    public static <T extends DomainEntityDTO> int hashCode(T dto){
        return dto.hashCode();
    }
}
